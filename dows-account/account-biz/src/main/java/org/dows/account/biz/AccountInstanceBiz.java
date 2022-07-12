package org.dows.account.biz;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.dows.account.crud.entity.*;
import org.dows.account.crud.service.*;
import org.dows.account.pojo.form.*;
import org.dows.account.pojo.query.AccountOwnerQuery;
import org.dows.account.pojo.query.AccountRoleQuery;
import org.dows.account.pojo.vo.AccountExcelVo;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.account.pojo.vo.SimpleAccountVo;
import org.dows.auth.api.util.UserUtil;
import org.dows.framework.api.enums.LoginType;
import org.dows.framework.api.exceptions.BizException;
import org.dows.framework.utils.ConvertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 账号-实例维度信息(AccountInstance)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:53:32
 */

@Service
@Slf4j
public class AccountInstanceBiz {
    @Resource
    private AccountInstanceService accountInstanceService;
    @Resource
    private AccountIdentifierService accountIdentifierService;
    @Resource
    private PrincipalRoleService principalRoleService;
    @Resource
    private AccountTenantService accountTenantService;
    @Resource
    private AccountGroupService accountGroupService;
    @Resource
    private SmsClient smsClient;
    @Resource
    private TenantInstanceBiz tenantInstanceBiz;
    @Resource
    private AccountOrganizationBiz accountOrganizationBiz;

    public IPage<AccountVo> queryUserList(UserListQuery userListQuery) {
        if (UserUtil.isTenant()) {
            userListQuery.setTenantId(UserUtil.getTenantId());
        }
        if (null != userListQuery.getIsAllUser() && userListQuery.getIsAllUser()) {
            if (UserUtil.isTenant()) {
                userListQuery.setNotTenantId(UserUtil.getTenantId());
                userListQuery.setTenantId(null);
            }
        }
        Page<AccountVo> pageInfo = new Page<>(userListQuery.getPage(), userListQuery.getSize());
        return accountInstanceService.queryUserList(pageInfo, userListQuery);
    }


    public List<SimpleAccountVo> queryUserListById(List<Long> ids) {
        return accountInstanceService.querySimpleUserByIds(ids);
    }

    @Transactional
    public String updateUser(UserUpdate userUpdate) {
        if (StringUtils.hasText(userUpdate.getPassword()) && StringUtils.hasText(userUpdate.getConfirmPassword())) {
            if (!userUpdate.getPassword().equals(userUpdate.getConfirmPassword())) {
                throw new BizException("2次密码不相同");
            }
            userUpdate.setPassword(PasswordUtil.encode(userUpdate.getPassword()));
        }
        preCheck(userUpdate);
        AccountInstance accountInstance = ConvertUtil.convert(userUpdate, new AccountInstance());
        accountInstance.setAccountPwd(userUpdate.getPassword());
        boolean result = accountInstanceService.saveOrUpdate(accountInstance);
        //插入手机 邮箱等
        updateLoginType(userUpdate, accountInstance.getId());
        //如果是租户 需要绑定租户
        if (null == userUpdate.getId() && null != UserUtil.getTenantId()) {
            List<AccountTenant> updateAccountTenantList = new ArrayList<>();
            AccountTenant accountTenant = new AccountTenant();
            accountTenant.setTenantId(UserUtil.getTenantId());
            accountTenant.setAccountId(accountInstance.getId());
            updateAccountTenantList.add(accountTenant);
            accountTenantService.updateAccountTenant(updateAccountTenantList);
        }
        return Constants.toResult(result);
    }

    //账户唯一性check
    private boolean preCheck(UserUpdate userUpdate) {
        //
      /*  boolean createFlag = true;
        if(null==userUpdate.getId()){
            createFlag=false;
        }*/
        //唯一性
        List<String> loginList = loginActionId(userUpdate);
        List<AccountIdDTO> accountIdDTOList = accountInstanceService.queryAccountIdList(loginList, userUpdate.getId());
        if (!CollectionUtils.isEmpty(accountIdDTOList)) {
            String desc = LoginType.of(accountIdDTOList.get(0).getLoginType()).getDesc();
            throw new BizException(desc + accountIdDTOList.get(0).getIdentifier() + "已存在");
        }
        return true;
    }

    private List<String> loginActionId(UserUpdate userUpdate) {
        List<String> resultList = new ArrayList<>();
        if (StringUtils.hasText(userUpdate.getPhone())) {
            resultList.add(userUpdate.getPhone());
        }
        if (StringUtils.hasText(userUpdate.getLoginName())) {
            resultList.add(userUpdate.getLoginName());
        }
        if (StringUtils.hasText(userUpdate.getEmail())) {
            resultList.add(userUpdate.getEmail());
        }
        if (StringUtils.hasText(userUpdate.getEmployeeNo())) {
            resultList.add(userUpdate.getEmployeeNo());
        }
        return resultList;
    }

    private String updateLoginType(UserUpdate userUpdate, Long id) {
        if (StringUtils.hasText(userUpdate.getPhone())) {
            saveOrUpdateIdAccount(LoginType.phone, id, userUpdate.getPhone());
        }
        if (StringUtils.hasText(userUpdate.getLoginName())) {
            saveOrUpdateIdAccount(LoginType.login, id, userUpdate.getLoginName());
        }
        if (StringUtils.hasText(userUpdate.getEmail())) {
            saveOrUpdateIdAccount(LoginType.email, id, userUpdate.getEmail());
        }
        if (StringUtils.hasText(userUpdate.getEmployeeNo())) {
            saveOrUpdateIdAccount(LoginType.employeeNo, id, userUpdate.getEmployeeNo());
        }
    }

    private String saveOrUpdateIdAccount(LoginType loginType, Long accountId, String value) {
        if (!StringUtils.hasText(value)) {
            return Constants.SUCCESS;
        }
        //更新 删除所有
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        accountIdentifier.setAccountId(accountId);
        accountIdentifier.setLoginType(loginType.getValue());
        accountIdentifier.setIdentifier(value);
        accountIdentifierService.updateAccountIdentifier(accountIdentifier);
        return Constants.SUCCESS;
    }

    public String delUser(List<Long> ids) {
        return Constants.toResult(accountInstanceService.update(
                new UpdateWrapper<AccountInstance>().set("deleted", 1).in("id", ids)));
    }

    public String batchUpdateStatus(BatchUpdateStatusForm batchUpdateStatus) {
        UpdateWrapper<AccountInstance> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", batchUpdateStatus.getStatus().getValue())
                .in("id", batchUpdateStatus.getIds());
        return Constants.toResult(accountInstanceService.update(updateWrapper));
    }

    public String restPassword(List<ReqResetPwd> reqResetPwds) {
        reqResetPwds.forEach(item -> {
            restPasswordItem(item);
        });
        return "success";
    }

    public String restPasswordItem(ReqResetPwd reqResetPwd) {

        //获取用用户
        AccountInstance accountInstance = accountInstanceService.getById(reqResetPwd.getUserId());
        String org = accountInstance.getLoginName() + "123";
        String password = genPassword(org);
        if (null == reqResetPwd.getSend()) {
            reqResetPwd.setSend(false);
        }
        if (StringUtils.hasText(accountInstance.getPhone()) && reqResetPwd.getSend()) {
            try {
                /*smsClient.sendIndustry(new AuthConfig(), new SmsIndustryParmas("超市", accountInstance.getPhone(),
                        StringUtil.format("云平台 初始密码{0} 请及时修改密码", org), "1"));*/
            } catch (BizException e) {
                log.error("sms send", e);
                throw new BizException(e.getMessage());
            }
        }
        return Constants.toResult(accountInstanceService.update(
                new UpdateWrapper<AccountInstance>().lambda().set(AccountInstance::getAccountPwd, password)
                        .eq(AccountInstance::getId, reqResetPwd.getUserId())
                        .eq(AccountInstance::getDeleted, 0)
        ));
    }

    private String genPassword(String org) {
        return PasswordUtil.encode(org);
    }

    public String platRoleSet(UserRoleRelReq userRoleRelReq) {
        //全部删除
        Long accountId = userRoleRelReq.getAccountId();
        principalRoleService.deleteRoleRel(Arrays.asList(accountId), 0, 1);

        List<Long> roleIdList = userRoleRelReq.getRoleIdList();
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Constants.FAIL;
        }
        List<PrincipalRole> principalRoles = new ArrayList<>();
        userRoleRelReq.getRoleIdList().forEach(roleId -> {
            PrincipalRole principalRole = new PrincipalRole();
            principalRole.setRoleId(roleId);
            principalRole.setPrincipalId(accountId);
            principalRole.setPrincipalTyp(0);
            principalRoles.add(principalRole);

        });
        principalRoleService.updatePrincipalRole(principalRoles);
        return Constants.SUCCESS;
    }

    public List<Long> accountRoleList(AccountRoleQuery reqAccountRole) {
        List<RoleVo> principalRoleList = principalRoleService.accountRoleList(reqAccountRole);
             /*   .list(new QueryWrapper<PrincipalRole>().lambda().eq(PrincipalRole::getPrincipalId, 0)
                .eq(PrincipalRole::getPrincipalId, reqAccountRole.getAccountId())
                        .eq(null!=reqAccountRole.getIsPlatform(),Pri)
                        );*/
        return principalRoleList.stream().map(RoleVo::getId).collect(Collectors.toList());
    }

    public IPage<TenantVo> accountOwnerQuery(AccountOwnerQuery accountOwnerQuery) {
        Page<TenantVo> pageInfo = new Page<>(accountOwnerQuery.getPage(), accountOwnerQuery.getSize());
        IPage<TenantVo> page = accountTenantService.queryAccountTenantList(pageInfo, accountOwnerQuery);
        Map<Long, CountDTO> countAccountMap = new HashMap<>();
        Map<Long, CountDTO> countGroupMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(page.getRecords())) {
            List<Long> tenantIds = page.getRecords().stream().map(TenantVo::getId).collect(Collectors.toList());
            countAccountMap = tenantInstanceBiz.countAccountMap(tenantIds);
            countGroupMap = tenantInstanceBiz.countGroupMap(tenantIds);
        }
        Map<Long, CountDTO> finalCountAccountMap = countAccountMap;
        Map<Long, CountDTO> finalCountGroupMap = countGroupMap;
        page.getRecords().forEach(tenantVo -> {
            tenantVo.setUserCount(0L);
            tenantVo.setGroupCount(0L);
            if (finalCountAccountMap.containsKey(tenantVo.getId())) {
                tenantVo.setUserCount(finalCountAccountMap.get(tenantVo.getId()).getNum());
            }
            if (finalCountGroupMap.containsKey(tenantVo.getId())) {
                tenantVo.setGroupCount(finalCountGroupMap.get(tenantVo.getId()).getNum());
            }
        });
        return page;
    }

    public IPage<GroupVo> accountGroupList(AccountOwnerQuery accountOwnerQuery) {
        if (UserUtil.isTenant()) {
            accountOwnerQuery.setTenantId(UserUtil.getTenantId());
        }
        Page<TenantVo> pageInfo = new Page<>(accountOwnerQuery.getPage(), accountOwnerQuery.getSize());
        IPage<GroupVo> page = accountGroupService.accountGroupList(pageInfo, new QueryWrapper<AccountGroup>()
                .eq("t.account_id", accountOwnerQuery.getAccountId())
                .eq(null != accountOwnerQuery.getTenantId(), "b.tenant_id", accountOwnerQuery.getTenantId())
                .eq("t.deleted", 0));

        Map<Long, CountDTO> countDTOMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(page.getRecords())) {
            List<Long> tenantIds = page.getRecords().stream().map(GroupVo::getId).collect(Collectors.toList());
            countDTOMap = accountOrganizationBiz.countDTOMap(tenantIds);
        }
        Map<Long, CountDTO> finalCountDTOMap = countDTOMap;
        page.getRecords().forEach(groupVo -> {
            groupVo.setUserCount(0L);
            if (finalCountDTOMap.containsKey(groupVo.getId())) {
                groupVo.setUserCount(finalCountDTOMap.get(groupVo.getId()).getNum());
            }
        });

        return page;
    }

    public File exportData(UserListQuery userListQuery) {
        String fileName = "user_list_" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        userListQuery.setPage(1);
        userListQuery.setSize(999999);
        File file = new File(fileName);
        EasyExcel.write(file, AccountExcelVo.class).sheet("账户").doWrite(ConvertUtil.converts(queryUserList(userListQuery).getRecords(), AccountExcelVo.class));
        return file;
    }

    public void importExcel(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), AccountExcelVo.class, new AnalysisEventListener<AccountExcelVo>() {
                        @Override
                        public void invoke(AccountExcelVo accountExcelVo, AnalysisContext analysisContext) {
                            //try{
                            UserUpdate userUpdate = new UserUpdate();
                            userUpdate = ConvertUtil.convert(accountExcelVo, userUpdate);
                            preCheck(userUpdate);
                            AccountInstance accountInstance = ConvertUtil.convert(userUpdate, new AccountInstance());
                            boolean result = accountInstanceService.saveOrUpdate(accountInstance);
                            //插入手机 邮箱等
                            updateLoginType(userUpdate, accountInstance.getId());
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                        }
                    })
                    .sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String changePassword(ChangePasswordForm reqChangePassword) {
        Long userId = UserUtil.getUserId();
        AccountInstance accountInstance = accountInstanceService.getById(userId);
        boolean match = PasswordUtil.match(reqChangePassword.getOrgPassword(), accountInstance.getAccountPwd());
        if (!match) {
            throw new BizException("原始密码输入错误");
        }
        if (!reqChangePassword.getNewPassword().equals(reqChangePassword.getConfirmPassword())) {
            throw new BizException("二次输入密码不同");
        }
        return Constants.toResult(accountInstanceService.update(
                new UpdateWrapper<AccountInstance>().lambda().set(AccountInstance::getAccountPwd, PasswordUtil.encode(reqChangePassword.getNewPassword()))
                        .eq(AccountInstance::getId, userId)
                        .eq(AccountInstance::getDeleted, 0)
        ));
    }
}
