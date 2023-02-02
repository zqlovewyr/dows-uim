package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountGroupApi;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import org.dows.account.biz.util.RangeUtil;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.biz.enums.EnumAccountRolePrincipalType;
import org.dows.account.entity.*;
import org.dows.account.service.*;
import org.dows.account.vo.AccountGroupVo;
import org.dows.account.vo.NormalDataVo;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserContactApi;
import org.dows.user.api.api.UserInstanceApi;
import org.dows.user.api.dto.UserContactDTO;
import org.dows.user.api.dto.UserInstanceDTO;
import org.dows.user.api.vo.UserContactVo;
import org.dows.user.api.vo.UserInstanceVo;
import org.dows.user.entity.UserContact;
import org.dows.user.entity.UserInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Author：wHuan
 * @Date：2022/11/25 16:10
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AccountGroupBiz implements AccountGroupApi {

    private final AccountGroupService accountGroupService;

    private final AccountRoleService accountRoleService;

    private final AccountUserService accountUserService;

    private final AccountGroupInfoService accountGroupInfoService;

    private final UserInstanceApi userInstanceApi;

    private final AccountInstanceService accountInstanceService;

    private final UserContactApi userContactApi;

    /**
     * 根据组织ids 查询对应角色
     * roleId 若为空则查询所有用户
     *
     * @param accountOrgIds 组织IDS
     * @param roleCode      指定rbacRoleCode
     * @return 返回每个组织下对应rbacRoleCode的AccountGroupList
     */
    public Map<String, List<AccountGroup>> mapAccountGroupByRole(List<String> accountOrgIds, String roleCode) {
        if (CollectionUtils.isEmpty(accountOrgIds)) {
            return Collections.emptyMap();
        }
        List<AccountGroup> accountGroupList = accountGroupService.lambdaQuery()
                .in(AccountGroup::getOrgId, accountOrgIds)
                .eq(AccountGroup::getDeleted, false)
                .list();
        if (StringUtils.isNotBlank(roleCode)) {
            // list accountRole by roleId
            List<AccountRole> accountRoleList = accountRoleService.lambdaQuery()
                    .select(AccountRole::getPrincipalId)
                    .eq(AccountRole::getRoleCode, roleCode)
                    .eq(AccountRole::getPrincipalType, EnumAccountRolePrincipalType.PERSONAL.getCode())
                    .list();
            if (CollectionUtils.isEmpty(accountRoleList)) {
                return Collections.emptyMap();
            }
            List<AccountGroup> collect = accountGroupList.stream()
                    .filter(item ->
                            accountRoleList.stream()
                                    .map(AccountRole::getPrincipalId)
                                    .collect(Collectors.toList()).contains(item.getAccountId())
                    ).collect(Collectors.toList());
            return collect.stream().collect(Collectors.groupingBy(AccountGroup::getOrgId, HashMap::new, Collectors.toList()));
        }
        return accountGroupList.stream().collect(Collectors.groupingBy(AccountGroup::getOrgId, HashMap::new, Collectors.toList()));
    }

    /**
     * batch insert account-group
     * 批量创建 账号-组
     *
     * @param accountGroupDTOs account-groups
     */
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> batchInsertGroup(List<AccountGroupDTO> accountGroupDTOs) {
        AtomicBoolean flag = new AtomicBoolean(true);
        if (CollectionUtils.isEmpty(accountGroupDTOs)) {
            return Response.ok(false);
        }
        accountGroupDTOs.forEach(account -> {
            //1、创建账号实例
            AccountInstance accountInstance = new AccountInstance();
            BeanUtils.copyProperties(account, accountInstance);
            boolean accountFlag = accountInstanceService.save(accountInstance);
            if (accountFlag == false) {
                flag.set(false);
            }
            //2、创建用户实例
            UserInstanceDTO userInstance = new UserInstanceDTO();
            BeanUtils.copyProperties(account, userInstance);
            Long id = userInstanceApi.insertUserInstance(userInstance).getData();
            if (userInstance.getId() == null) {
                flag.set(false);
            }
            //3、设置关联关系
            AccountUser accountUser = new AccountUser();
            BeanUtils.copyProperties(account, accountUser);
            accountUser.setUserId(id.toString());
            accountUser.setAccountId(accountInstance.getId().toString());
            boolean unionFlag = accountUserService.save(accountUser);
            if (unionFlag == false) {
                flag.set(false);
            }
            //4、创建组员实例
            AccountGroup accountGroup = new AccountGroup();
            BeanUtils.copyProperties(account, accountGroup);
            accountGroup.setUserId(id.toString());
            boolean groupFlag = accountGroupService.save(accountGroup);
            if (groupFlag == false) {
                flag.set(false);
            }
        });
        return Response.ok(flag.get());
    }

    /**
     * 自定义账号-组-成员 列表
     *
     * @param accountGroupDTO
     * @return Response<IPage < AccountGroupVo>>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<IPage<AccountGroupVo>> customAccountGroupList(AccountGroupDTO accountGroupDTO) {
        //1、获取角色对应账号Id
        Set<String> accountIds = new HashSet<>();
        if (accountGroupDTO.getRoleId() != null) {
            List<AccountRole> accountRoleList = accountRoleService.lambdaQuery()
                    .select(AccountRole::getPrincipalId)
                    .eq(AccountRole::getRoleId, accountGroupDTO.getRoleId())
                    .eq(AccountRole::getPrincipalType, EnumAccountRolePrincipalType.PERSONAL.getCode())
                    .eq(AccountRole::getDeleted, false)
                    .list();
            if (accountRoleList != null && accountRoleList.size() > 0) {
                accountRoleList.forEach(accountRole -> {
                    accountIds.add(accountRole.getPrincipalId());
                });
            }
        }
        //2、根据团队负责人获取对应组织信息
        Set<String> orgIds = new HashSet<>();
        if (StringUtils.isNotEmpty(accountGroupDTO.getOwnerId())) {
            List<AccountGroupInfo> groupInfoList = accountGroupInfoService.lambdaQuery()
                    .select(AccountGroupInfo::getOrgId)
                    .eq(AccountGroupInfo::getAccountId, accountGroupDTO.getOwnerId())
                    .eq(AccountGroupInfo::getDeleted, false)
                    .list();
            if (groupInfoList != null && groupInfoList.size() > 0) {
                groupInfoList.forEach(accountGroupInfo -> {
                    orgIds.add(accountGroupInfo.getOrgId());
                });
            }
        }
        //3、根据用户信息获取用户Id
        //3.1、根据用户电话获取对应用户Id
        Set<String> userIds = new HashSet<>();
        if (accountGroupDTO.getUserIds() != null && accountGroupDTO.getUserIds().size() > 0) {
            userIds.addAll(accountGroupDTO.getUserIds());
        }
        if (StringUtils.isNotEmpty(accountGroupDTO.getContactNum())) {
            UserContactDTO dto = new UserContactDTO();
            dto.setContactNum(accountGroupDTO.getContactNum());
            List<UserContactVo> userContactList = userContactApi.getUserContactList(dto).getData();
            List<UserContact> contactList = new ArrayList<>();
            if (userContactList != null && userContactList.size() > 0) {
                userContactList.forEach(model -> {
                    UserContact contact = new UserContact();
                    BeanUtils.copyProperties(model, contact);
                    contactList.add(contact);
                });
            }
            if (userContactList != null && userContactList.size() > 0) {
                userContactList.forEach(userContact -> {
                    userIds.add(userContact.getId().toString());
                });
            }
        }
        //3.2、根据用户身份证号获取对应用户Id
        if (StringUtils.isNotEmpty(accountGroupDTO.getIdNo())) {
            UserInstanceDTO dto = new UserInstanceDTO();
            dto.setIdNo(accountGroupDTO.getIdNo());
            List<UserInstanceVo> userInstanceList = userInstanceApi.getUserInstanceList(dto).getData();
            List<UserInstance> instanceList = new ArrayList<>();
            if (userInstanceList != null && userInstanceList.size() > 0) {
                userInstanceList.forEach(model -> {
                    UserInstance instance = new UserInstance();
                    BeanUtils.copyProperties(model, instance);
                    instanceList.add(instance);
                });
            }
            if (instanceList != null && instanceList.size() > 0) {
                instanceList.forEach(userContact -> {
                    userIds.add(userContact.getId().toString());
                });
            }
        }

        //3.3、根据用户姓名获取对应用户id
        if (StringUtils.isNotEmpty(accountGroupDTO.getName())) {
            UserInstanceDTO dto = new UserInstanceDTO();
            dto.setIdNo(accountGroupDTO.getName());
            List<UserInstanceVo> userInstanceList = userInstanceApi.getUserInstanceList(dto).getData();
            List<UserInstance> instanceList = new ArrayList<>();
            if (userInstanceList != null && userInstanceList.size() > 0) {
                userInstanceList.forEach(model -> {
                    UserInstance instance = new UserInstance();
                    BeanUtils.copyProperties(model, instance);
                    instanceList.add(instance);
                });
            }
            if (instanceList != null && instanceList.size() > 0) {
                instanceList.forEach(userContact -> {
                    userIds.add(userContact.getId().toString());
                });
            }
        }

        //4、查询
        LambdaQueryWrapper<AccountGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(accountGroupDTO.getAppId()), AccountGroup::getAppId, accountGroupDTO.getAppId())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getAccountId()), AccountGroup::getAccountId, accountGroupDTO.getAccountId())
                .in(accountIds != null && accountIds.size() > 0, AccountGroup::getAccountId, accountIds)
                .eq(accountGroupDTO.getId() != null, AccountGroup::getId, accountGroupDTO.getId())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getOrgId()), AccountGroup::getOrgId, accountGroupDTO.getOrgId())
                .in(orgIds != null && orgIds.size() > 0, AccountGroup::getOrgId, orgIds)
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgName()), AccountGroup::getOrgName, accountGroupDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountName()), AccountGroup::getAccountName, accountGroupDTO.getAccountName())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getTenantId()), AccountGroup::getTenantId, accountGroupDTO.getTenantId())
                .eq(accountGroupDTO.getDt() != null, AccountGroup::getDt, accountGroupDTO.getDt())
                .gt(accountGroupDTO.getStartTime() != null, AccountGroup::getDt, accountGroupDTO.getStartTime())
                .lt(accountGroupDTO.getEndTime() != null, AccountGroup::getDt, accountGroupDTO.getEndTime())
                .eq(AccountGroup::getDeleted, false)
                .orderByDesc(AccountGroup::getDt);
        Page<AccountGroup> page = new Page<>(accountGroupDTO.getPageNo(), accountGroupDTO.getPageSize());
        IPage<AccountGroup> groupPage = accountGroupService.page(page, queryWrapper);
        //5、获取成员名称
        List<AccountGroup> recordList = groupPage.getRecords();
        List<AccountGroupVo> voList = new ArrayList<>();
        recordList.forEach(record -> {
            AccountGroupVo vo = new AccountGroupVo();
            BeanUtils.copyProperties(record, vo);
            //5.1、设置成员名称和角色
            LambdaQueryWrapper<AccountRole> roleWrapper = new LambdaQueryWrapper<>();
            AccountRole role = accountRoleService.getOne(roleWrapper.eq(AccountRole::getPrincipalId, vo.getAccountId())
                    .eq(AccountRole::getDeleted, false)
                    .eq(AccountRole::getPrincipalType, EnumAccountRolePrincipalType.PERSONAL.getCode()));
            //非空判断
            if (role != null && StringUtils.isNotEmpty(role.getRoleName())) {
                vo.setRoleName(role.getRoleName());
            }
            //5.2、设置成员相关信息
            AccountUser user = new AccountUser();
            UserInstance instance = new UserInstance();
            UserContact contact = new UserContact();
            LambdaQueryWrapper<AccountUser> userWrapper = new LambdaQueryWrapper<>();
            user = accountUserService.getOne(userWrapper.eq(AccountUser::getAccountId, vo.getAccountId()));
            if (user != null) {
                LambdaQueryWrapper<UserInstance> instanceWrapper = new LambdaQueryWrapper<>();
                UserInstanceDTO dto = new UserInstanceDTO();
                dto.setId(Long.valueOf(user.getUserId()));
                UserInstanceVo model = new UserInstanceVo();
                model = userInstanceApi.getUserInstanceList(dto).getData().get(0);
                //复制属性
                BeanUtils.copyProperties(model, instance);
                UserContactDTO contactDto = new UserContactDTO();
                contactDto.setUserId(user.getUserId());
                List<UserContactVo> contactVoList = new ArrayList<>();
                contactVoList = userContactApi.getUserContactList(contactDto).getData();
                if(contactVoList != null && contactVoList.size() > 0){
                    //复制属性
                    BeanUtils.copyProperties(contactVoList.get(0), contact);
                }

            }
            //非空判断
            if (instance != null) {
                if (StringUtils.isNotEmpty(instance.getGender())) {
                    vo.setGender(instance.getGender());
                }
                if (StringUtils.isNotEmpty(instance.getName())) {
                    vo.setUserName(instance.getName());
                }
                if (StringUtils.isNotEmpty(instance.getIdNo())) {
                    vo.setIdNo(instance.getIdNo());
                }
            }
            if (contact != null) {
                if (StringUtils.isNotEmpty(contact.getContactNum())) {
                    vo.setContactNum(contact.getContactNum());
                }
            }
            if (accountGroupDTO != null) {
                if (accountGroupDTO.getExamineTime() != null) {
                    vo.setExamineTime(accountGroupDTO.getExamineTime());
                }
                if (accountGroupDTO.getRecordTime() != null) {
                    vo.setRecordTime(accountGroupDTO.getRecordTime());
                }
            }
            //5.3、设置组织架构负责人
            LambdaQueryWrapper<AccountGroupInfo> ownerWrapper = new LambdaQueryWrapper<>();
            AccountGroupInfo groupInfo = accountGroupInfoService.getOne(ownerWrapper.eq(AccountGroupInfo::getOrgId, vo.getOrgId())
                    .eq(AccountGroupInfo::getDeleted, false));
            //非空判断
            if (groupInfo != null) {
                if (StringUtils.isNotEmpty(groupInfo.getAccountId())) {
                    vo.setOwnerId(groupInfo.getAccountId());
                }
                if (StringUtils.isNotEmpty(groupInfo.getOwner())) {
                    vo.setOwnerName(groupInfo.getOwner());
                }
            }
            voList.add(vo);
        });
        //复制属性
        IPage<AccountGroupVo> pageVo = new Page<>();
        BeanUtils.copyProperties(groupPage, pageVo, new String[]{"records"});
        pageVo.setRecords(voList);
        return Response.ok(pageVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Map<String, Object>> insertOrUpdateAccountGroup(AccountGroupDTO accountGroupDTO) {
        Map<String, Object> map = new HashMap<>();
        //1、创建账号实例
        AccountInstance accountInstance = new AccountInstance();
        BeanUtils.copyProperties(accountGroupDTO, accountInstance);
        boolean accountFlag = accountInstanceService.saveOrUpdate(accountInstance);
        if (accountFlag == false) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_CREATE_FAIL_EXCEPTION);
        }
        map.put("accountId", accountInstance.getId());

        //2、创建用户实例
        UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
        BeanUtils.copyProperties(accountGroupDTO, userInstanceDTO);
        Long userId = userInstanceApi.insertUserInstance(userInstanceDTO).getData();
        map.put("userId", userId);

        //3、设置关联关系
        AccountUser accountUser = new AccountUser();
        BeanUtils.copyProperties(accountGroupDTO, accountUser);
        accountUser.setUserId(userId.toString());
        accountUser.setAccountId(accountInstance.getId().toString());
        boolean unionFlag = accountUserService.save(accountUser);
        if (unionFlag == false) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_USER_UNION_FAIL_EXCEPTION);
        }
        map.put("unionId", accountUser.getId());

        //4、创建组员实例
        AccountGroup accountGroup = new AccountGroup();
        BeanUtils.copyProperties(accountGroupDTO, accountGroup);
        accountGroup.setUserId(userId.toString());
        boolean groupFlag = accountGroupService.save(accountGroup);
        if (groupFlag == false) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_GROUP_MEMBER_FAIL_EXCEPTION);
        }
        map.put("groupId", accountGroup.getId());

        return Response.ok(map);
    }

    @Override
    // TODO 2023/01/12
    public Response<Object> downloadExcelTemplate(HttpServletResponse response) {
        return null;
    }

    @Override
    // TODO 2023/01/12
    public Response<Object> uploadAccountGroup(MultipartFile file) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> batchDeleteGroupMembers(List<AccountGroupDTO> accountGroupDTOs) {
        if (CollectionUtils.isEmpty(accountGroupDTOs)) {
            return Response.ok(false);
        }
        accountGroupDTOs.forEach(item -> {
            LambdaUpdateWrapper<AccountGroup> groupWrapper = Wrappers.lambdaUpdate(AccountGroup.class);
            groupWrapper.set(AccountGroup::getDeleted, true)
                    .eq(AccountGroup::getOrgId, item.getOrgId());
            accountGroupService.update(groupWrapper);
        });
        return Response.ok(true);
    }

    @Override
    public Response<List<NormalDataVo>> getAgeRateList(AccountGroupDTO accountGroupDTO) {
        List<NormalDataVo> dataList = new ArrayList<>();
        //1、获取客户列表
        List<AccountGroup> groupList = accountGroupService.lambdaQuery()
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgId()), AccountGroup::getOrgId, accountGroupDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgName()), AccountGroup::getOrgName, accountGroupDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountId()), AccountGroup::getAccountId, accountGroupDTO.getAccountId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountName()), AccountGroup::getAccountName, accountGroupDTO.getAccountName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getUserId()), AccountGroup::getUserId, accountGroupDTO.getUserId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAppId()), AccountGroup::getAppId, accountGroupDTO.getAppId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getTenantId()), AccountGroup::getTenantId, accountGroupDTO.getTenantId())
                .eq(accountGroupDTO.getDt() != null, AccountGroup::getDt, accountGroupDTO.getDt())
                .gt(accountGroupDTO.getStartTime() != null, AccountGroup::getDt, accountGroupDTO.getStartTime())
                .lt(accountGroupDTO.getEndTime() != null, AccountGroup::getDt, accountGroupDTO.getEndTime())
                .orderByDesc(AccountGroup::getDt).list();
        //2、获取对应用户表年龄
        List<AccountGroupVo> voList = new ArrayList<>();
        if (groupList != null && groupList.size() > 0) {
            groupList.forEach(vo -> {
                AccountUser accountUser = accountUserService.lambdaQuery()
                        .eq(AccountUser::getAccountId, vo.getAccountId())
                        .one();
                if (accountUser == null) {
                    throw new AccountException(EnumAccountStatusCode.ACCOUNT_USER_NOT_EXIST_EXCEPTION);
                }
                UserInstanceVo instanceVo = userInstanceApi.getUserInstanceById(Long.valueOf(accountUser.getUserId())).getData();
                AccountGroupVo groupVo = new AccountGroupVo();
                //复制属性
                BeanUtils.copyProperties(vo, groupVo);
                //设置年龄
                if(StringUtils.isNotEmpty(instanceVo.getAge())){
                    groupVo.setAge(instanceVo.getAge());
                }
                voList.add(groupVo);
            });
        }
      //3、去除年龄为空的数据
      List<AccountGroupVo> list = voList.stream().filter(AccountGroupVo -> AccountGroupVo.getAge() != null).collect(Collectors.toList());
      //4、按年龄分组
      List<String> ageList = Arrays.asList(new String[]{"0~1","1~3","3~6","6~12","12~18","18~45","45~65","65以上"});
      ageList.forEach(age->{
          //4.1 0~1
          if(age.equals("0~1")){
              AtomicReference<Integer> num1 = new AtomicReference<>(0);
              list.forEach(vo->{
                  boolean flag1 = RangeUtil.inNumRange(Integer.parseInt(vo.getAge()),"[0,1)");
                  if(flag1 == true){
                     num1.getAndSet(num1.get() + 1);
                  }
              });
              NormalDataVo data = new NormalDataVo(age,num1.get().longValue());
              dataList.add(data);
          }
         //4.2 1~3
          if(age.equals("1~3")){
              AtomicReference<Integer> num1 = new AtomicReference<>(0);
              list.forEach(vo->{
                  boolean flag1 = RangeUtil.inNumRange(Integer.parseInt(vo.getAge()),"[1,3)");
                  if(flag1 == true){
                      num1.getAndSet(num1.get() + 1);
                  }
              });
              NormalDataVo data = new NormalDataVo(age,num1.get().longValue());
              dataList.add(data);
          }
          //4.3 3~6
          if(age.equals("3~6")){
              AtomicReference<Integer> num1 = new AtomicReference<>(0);
              list.forEach(vo->{
                  boolean flag1 = RangeUtil.inNumRange(Integer.parseInt(vo.getAge()),"[3,6)");
                  if(flag1 == true){
                      num1.getAndSet(num1.get() + 1);
                  }
              });
              NormalDataVo data = new NormalDataVo(age,num1.get().longValue());
              dataList.add(data);
          }
          //4.4 6~12
          if(age.equals("6~12")){
              AtomicReference<Integer> num1 = new AtomicReference<>(0);
              list.forEach(vo->{
                  boolean flag1 = RangeUtil.inNumRange(Integer.parseInt(vo.getAge()),"[6,12)");
                  if(flag1 == true){
                      num1.getAndSet(num1.get() + 1);
                  }
              });
              NormalDataVo data = new NormalDataVo(age,num1.get().longValue());
              dataList.add(data);
          }
          //4.5 12~18
          if(age.equals("12~18")){
              AtomicReference<Integer> num1 = new AtomicReference<>(0);
              list.forEach(vo->{
                  boolean flag1 = RangeUtil.inNumRange(Integer.parseInt(vo.getAge()),"[12,18)");
                  if(flag1 == true){
                      num1.getAndSet(num1.get() + 1);
                  }
              });
              NormalDataVo data = new NormalDataVo(age,num1.get().longValue());
              dataList.add(data);
          }
          //4.6 18~45
          if(age.equals("18~45")){
              AtomicReference<Integer> num1 = new AtomicReference<>(0);
              list.forEach(vo->{
                  boolean flag1 = RangeUtil.inNumRange(Integer.parseInt(vo.getAge()),"[18,45)");
                  if(flag1 == true){
                      num1.getAndSet(num1.get() + 1);
                  }
              });
              NormalDataVo data = new NormalDataVo(age,num1.get().longValue());
              dataList.add(data);
          }
          //4.7 45~65
          if(age.equals("18~45")){
              AtomicReference<Integer> num1 = new AtomicReference<>(0);
              list.forEach(vo->{
                  boolean flag1 = RangeUtil.inNumRange(Integer.parseInt(vo.getAge()),"[45,65)");
                  if(flag1 == true){
                      num1.getAndSet(num1.get() + 1);
                  }
              });
              NormalDataVo data = new NormalDataVo(age,num1.get().longValue());
              dataList.add(data);
          }
          //4.8 65以上
          if(age.equals("65以上")){
              AtomicReference<Integer> num1 = new AtomicReference<>(0);
              list.forEach(vo->{
                  boolean flag1 = RangeUtil.inNumRange(Integer.parseInt(vo.getAge()),"[65,]");
                  if(flag1 == true){
                      num1.getAndSet(num1.get() + 1);
                  }
              });
              NormalDataVo data = new NormalDataVo(age,num1.get().longValue());
              dataList.add(data);
          }
      });
      return Response.ok(dataList);
    }

    @Override
    public Response<List<AccountGroupVo>> getAccountGroupList(AccountGroupDTO accountGroupDTO) {
        List<AccountGroup> groupList = accountGroupService.lambdaQuery()
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgId()), AccountGroup::getOrgId, accountGroupDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgName()), AccountGroup::getOrgName, accountGroupDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountId()), AccountGroup::getAccountId, accountGroupDTO.getAccountId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountName()), AccountGroup::getAccountName, accountGroupDTO.getAccountName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getUserId()), AccountGroup::getUserId, accountGroupDTO.getUserId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAppId()), AccountGroup::getAppId, accountGroupDTO.getAppId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getTenantId()), AccountGroup::getTenantId, accountGroupDTO.getTenantId())
                .eq(accountGroupDTO.getDt() != null, AccountGroup::getDt, accountGroupDTO.getDt())
                .gt(accountGroupDTO.getStartTime() != null, AccountGroup::getDt, accountGroupDTO.getStartTime())
                .lt(accountGroupDTO.getEndTime() != null, AccountGroup::getDt, accountGroupDTO.getEndTime())
                .orderByDesc(AccountGroup::getDt).list();
        //复制属性
        List<AccountGroupVo> voList = new ArrayList<>();
        groupList.forEach(group->{
            AccountGroupVo vo = new AccountGroupVo();
            BeanUtils.copyProperties(group,vo);
            voList.add(vo);
        });
        return Response.ok(voList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Map<String,Object>> updateAccountGroupById(AccountGroupDTO accountGroupDTO) {
        Map<String, Object> map = new HashMap<>();
        //1、更新账号实例
        AccountInstance accountInstance = new AccountInstance();
        BeanUtils.copyProperties(accountGroupDTO, accountInstance);
        boolean accountFlag = accountInstanceService.saveOrUpdate(accountInstance);
        if (accountFlag == false) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_CREATE_FAIL_EXCEPTION);
        }
        map.put("accountId", accountInstance.getId());

        //2、更新用户实例
        UserInstanceDTO dto = new UserInstanceDTO();
        dto.setAccountId(accountGroupDTO.getAccountId());
        dto.setUserId(accountGroupDTO.getUserId());
        List<UserInstanceVo> instanceList = userInstanceApi.getUserInstanceList(dto).getData();
        UserInstance model = new UserInstance();
        if(instanceList != null && instanceList.size() > 0){
            BeanUtils.copyProperties(instanceList.get(0),model);
        }
        UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
        BeanUtils.copyProperties(model, userInstanceDTO);
        BeanUtils.copyProperties(accountGroupDTO, userInstanceDTO);
        Long userId = userInstanceApi.updateUserInstance(userInstanceDTO).getData();
        map.put("userId", userId);
        return Response.ok(map);
    }

    @Override
    public Response<Boolean> deleteAccountGroup(Long id) {
        AccountGroup accountGroup = accountGroupService.lambdaQuery()
                .eq(AccountGroup::getAccountId, id)
                .one();
        LambdaUpdateWrapper<AccountGroup> groupWrapper = Wrappers.lambdaUpdate(AccountGroup.class);
        groupWrapper.set(AccountGroup::getDeleted, true)
                .eq(AccountGroup::getId, accountGroup.getId());
        boolean flag = accountGroupService.update(groupWrapper);
        return Response.ok(flag);
    }
}
