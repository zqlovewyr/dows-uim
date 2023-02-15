package org.dows.account.biz;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dows.account.biz.dto.AccountInstanceDTO;
import org.dows.account.biz.dto.AccountInstanceResDTO;
import org.dows.account.biz.enums.EnumAccountRolePrincipalType;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import org.dows.account.biz.exception.OrgException;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.entity.*;
import org.dows.account.query.AccountInstanceQuery;
import org.dows.account.service.*;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.Response;
import org.dows.rbac.biz.enums.EnumRbacStatusCode;
import org.dows.rbac.biz.exception.RbacException;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.service.RbacRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import static org.dows.account.biz.util.AccountUtil.getKeyOfkIdentifierAppIdV;


/**
 * @author runsix
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountInstanceBiz {
    private final AccountInstanceService accountInstanceService;
    private final AccountIdentifierService accountIdentifierService;
    private final RbacRoleService rbacRoleService;
    private final AccountRoleService accountRoleService;
    private final AccountOrgService accountOrgService;
    private final AccountGroupService accountGroupService;

    private final AccountTenantService accountTenantService;
    private final AccountUserService accountUserService;
    private final AccountUserInfoService accountUserInfoService;

    public Response getAccountInstanceListPage(AccountInstanceQuery query){

        Page<AccountInstanceResVo> page = new Page<>(query.getOffset(), query.getSize());
        IPage<AccountInstanceResVo> listByPage = accountInstanceService.getListByPage(page,query);
        return Response.ok(listByPage);
    }
    public Response getAccountInstanceInfo(Long id){
        AccountInstanceQuery query = new AccountInstanceQuery();
        Page<AccountInstanceResVo> page = new Page<>(query.getOffset(), query.getSize());
        query.setId(id);
        IPage<AccountInstanceResVo> listByPage = accountInstanceService.getListByPage(page,query);
        return Response.ok(listByPage.getRecords().get(0));
    }
    public Response deleteById(Long id){

        AccountInstance accountInstance = accountInstanceService.lambdaQuery().select(AccountInstance::getAccountId).eq(AccountInstance::getId,id).oneOpt().orElseThrow(() -> {
            throw new OrgException("删除失败，数据不存在");
        });
        String accountId = accountInstance.getAccountId();
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("account_id",accountId);
        accountIdentifierService.removeByMap(columnMap);
        Map<String, Object> columnMapRole = new HashMap<>();
        columnMapRole.put("principal_id",accountId);
        accountRoleService.removeByMap(columnMapRole);

        accountGroupService.removeByMap(columnMap);
        accountUserInfoService.removeByMap(columnMap);
        accountUserService.removeByMap(columnMap);
        accountTenantService.removeByMap(columnMap);

        accountInstanceService.removeById(id);
        return Response.ok();
    }
    /**
     * runsix method process
     * 1.check whether accountIdentifier queried by appId & identifier exist
     * 2.check whether rbacRoleId exist
     * 3.check whether accountOrgOrgId exist
     * 4.save accountIdentifier
     * 5.save accountInstance
     * 6.save accountRole if rbacRoleId exist
     * 7.save accountGroup if orgId exist
     * 8.convert entity to vo and return
    */
    @Transactional(rollbackFor = Exception.class)
    public AccountInstanceVo createAccountInstance(AccountInstanceDTO accountInstanceDTO) {
        accountInstanceDTO = AccountUtil.validateAndTrimAccountInstanceDTO(accountInstanceDTO);
        /* runsix:1.check whether accountIdentifier queried by appId & identifier exist */
        accountIdentifierService.lambdaQuery()
                .select(AccountIdentifier::getId)
                .eq(AccountIdentifier::getAppId, accountInstanceDTO.getAppId())
                .eq(AccountIdentifier::getIdentifier, accountInstanceDTO.getIdentifier())
                .oneOpt()
                .ifPresent((a) -> {
                    throw new AccountException(EnumAccountStatusCode.ACCOUNT_EXIST_EXCEPTION);
                });
        /* runsix:2.check whether rbacRoleId exist */
        RbacRole rbacRole = null;
        if (Objects.nonNull(accountInstanceDTO.getRbacRoleId())) {
            rbacRole = rbacRoleService.lambdaQuery()
                    .select(RbacRole::getId, RbacRole::getRoleName, RbacRole::getRoleCode)
                    .eq(RbacRole::getId, accountInstanceDTO.getRbacRoleId())
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);
                    });
        }
        /* runsix:3.check whether accountOrgOrgId exist */
        AccountOrg accountOrg = null;
        if (StringUtils.isNotBlank(accountInstanceDTO.getAccountOrgOrgId())) {
            accountOrg = accountOrgService.lambdaQuery()
                    .select(AccountOrg::getOrgId, AccountOrg::getOrgName)
                    .eq(AccountOrg::getOrgId, accountInstanceDTO.getAccountOrgOrgId())
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new OrgException(EnumAccountStatusCode.ORG_NOT_EXIST_EXCEPTION);
                    });
        }
        /* runsix:4.save accountIdentifier */
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        BeanUtils.copyProperties(accountInstanceDTO, accountIdentifier);
        accountIdentifier.setAccountId(IdWorker.getIdStr());
        accountIdentifierService.save(accountIdentifier);
        /* runsix:5.save accountInstance */
        AccountInstance accountInstance = new AccountInstance();
        BeanUtils.copyProperties(accountInstanceDTO, accountInstance);
        accountInstance.setAccountId(accountIdentifier.getAccountId());
        accountInstance.setPassword(new BCryptPasswordEncoder().encode(accountInstanceDTO.getPassword()));
        accountInstanceService.save(accountInstance);
        /* runsix:6.save accountRole if rbacRoleId exist */
        if (Objects.nonNull(rbacRole)) {
            accountRoleService.save(
                    AccountRole
                            .builder()
                            .roleId(accountInstanceDTO.getRbacRoleId().toString())
                            .roleName(rbacRole.getRoleName())
                            .roleCode(rbacRole.getRoleCode())
                            .principalType(EnumAccountRolePrincipalType.PERSONAL.getCode())
                            .principalId(accountInstance.getAccountId())
                            .principalName(accountInstanceDTO.getAccountName())
                            .build()
            );
        }
        /* runsix:7.save accountGroup if orgId exist */
        if (Objects.nonNull(accountOrg)) {
            accountGroupService.save(
                    AccountGroup
                            .builder()
                            .orgId(accountInstanceDTO.getAccountOrgOrgId())
                            .orgName(accountOrg.getOrgName())
                            .accountId(accountInstance.getAccountId())
                            .accountName(accountInstance.getAccountName())
                            .appId(accountInstanceDTO.getAppId())
                            .build()
            );
        }
        /* runsix:8.convert entity to vo and return */
        AccountInstanceVo accountInstanceVo = new AccountInstanceVo();
        BeanUtils.copyProperties(accountInstance, accountInstanceVo);
        accountInstanceVo.setAccountId(Long.parseLong(accountInstance.getAccountId()));
        return accountInstanceVo;
    }

    public List<AccountInstanceDTO> getAccountInstanceDTOListByFile(MultipartFile file, String appId, Long rbacRoleId, String accountOrgOrgId, String password, String avatar, String source, String phone) {
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<AccountInstanceDTO> accountInstanceDTOList = new ArrayList<>();
        EasyExcel.read(inputStream, AccountInstanceDTO.class, new ReadListener<AccountInstanceDTO>() {
            @Override
            public void invoke(AccountInstanceDTO accountInstanceDTO, AnalysisContext analysisContext) {
                if (AccountUtil.excelValidateAccountInstanceDTO(accountInstanceDTO)) {
                    accountInstanceDTO.setAppId(appId);
                    if (Objects.isNull(accountInstanceDTO.getRbacRoleId()) && Objects.nonNull(rbacRoleId)) {
                        accountInstanceDTO.setRbacRoleId(rbacRoleId);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getAccountOrgOrgId()) && StringUtils.isNotBlank(accountOrgOrgId)) {
                        accountInstanceDTO.setAccountOrgOrgId(accountOrgOrgId);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getPassword()) && StringUtils.isNotBlank(password)) {
                        accountInstanceDTO.setPassword(password);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getAvatar()) && StringUtils.isNotBlank(avatar)) {
                        accountInstanceDTO.setAvatar(avatar);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getSource()) && StringUtils.isNotBlank(source)) {
                        accountInstanceDTO.setSource(source);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getPhone()) && StringUtils.isNotBlank(phone)) {
                        accountInstanceDTO.setPhone(phone);
                    }
                    accountInstanceDTOList.add(accountInstanceDTO);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        }).sheet().doRead();
        return accountInstanceDTOList;
    }

    /**
     * runsix method process
     * 1.check whether input appId & identifier duplicated
     * 2.check whether accountIdentifier queried by appId & identifier exist
     * 3.check whether rbacRoleId exist
     * 4.check whether accountOrgOrgId exist
     * 5.batch save accountIdentifier
     * 6.batch save accountInstance
     * 7.batch save accountRole if rbacRoleId exist
     * 8.batch save accountGroup if orgId exist
    */
    @Transactional(rollbackFor = Exception.class)
    public void batchCreateAccountInstance(List<AccountInstanceDTO> accountInstanceDTOList) {
        /* runsix:TODO 所有in查询都要判空。注意 */
        accountInstanceDTOList.parallelStream().forEach(AccountUtil::validateAndTrimAccountInstanceDTO);
        /* runsix:1.check whether input appId & identifier duplicated */
        ConcurrentHashMap<String, Set<String>> kIdentifierVAppIdSet = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> kIdentifierAppIdVAccountId = new ConcurrentHashMap<>();
        accountInstanceDTOList.parallelStream().forEach(accountInstanceDTO -> {
            String identifier = accountInstanceDTO.getIdentifier();
            String appId = accountInstanceDTO.getAppId();
            Set<String> appIdSet = kIdentifierVAppIdSet.get(identifier);
            if (Objects.isNull(appIdSet)) {
                CopyOnWriteArraySet<String> newAppIdSet = new CopyOnWriteArraySet<>();
                newAppIdSet.add(appId);
                kIdentifierVAppIdSet.put(identifier, newAppIdSet);
            } else {
                boolean appIdIsNotExist = appIdSet.add(appId);
                if (!appIdIsNotExist) {
                    throw new AccountException(EnumAccountStatusCode.BATCH_IMPORT_IDENTIFIER_DUPLICATED);
                } else {
                    kIdentifierVAppIdSet.put(identifier, appIdSet);
                }
            }
            kIdentifierAppIdVAccountId.put(getKeyOfkIdentifierAppIdV(identifier,appId), IdWorker.getIdStr());
        });
        if (kIdentifierVAppIdSet.isEmpty()) {
            return;
        }
        /* runsix:2.check whether accountIdentifier queried by appId & identifier exist */
        accountIdentifierService.lambdaQuery()
                .select(AccountIdentifier::getIdentifier, AccountIdentifier::getAppId)
                .in(AccountIdentifier::getIdentifier, kIdentifierVAppIdSet.keySet())
                .list()
                .parallelStream()
                .forEach(accountIdentifier -> {
                    String identifier = accountIdentifier.getIdentifier();
                    String appId = accountIdentifier.getAppId();
                    if (kIdentifierVAppIdSet.containsKey(identifier) && kIdentifierVAppIdSet.get(identifier).contains(appId)) {
                        throw new AccountException(identifier + "," + EnumAccountStatusCode.ACCOUNT_EXIST_EXCEPTION.getDescr());
                    }
                });
        /* runsix:3.check whether rbacRoleId exist */
        Set<Long> rbacRoleIdSet = accountInstanceDTOList.parallelStream()
                .map(AccountInstanceDTO::getRbacRoleId)
                .filter(Objects::nonNull).collect(Collectors.toSet());
        Map<Long, RbacRole> kRbacRoleIdVRbacRole = new ConcurrentHashMap<>();
        if (!rbacRoleIdSet.isEmpty()) {
            kRbacRoleIdVRbacRole = rbacRoleService.lambdaQuery()
                    .select(RbacRole::getId, RbacRole::getRoleName, RbacRole::getRoleCode)
                    .in(RbacRole::getId, rbacRoleIdSet)
                    .list()
                    .parallelStream()
                    .collect(Collectors.toMap(RbacRole::getId, rbacRole -> rbacRole));
            if (kRbacRoleIdVRbacRole.size() != rbacRoleIdSet.size()) {
                throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);
            }
        }
        /* runsix:4.check whether accountOrgOrgId exist */
        Set<String> accountOrgOrgIdSet = accountInstanceDTOList.parallelStream()
                .map(AccountInstanceDTO::getAccountOrgOrgId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<String, AccountOrg> kAccountOrgOrgIdVAccountOrg = new ConcurrentHashMap<>();
        if (!accountOrgOrgIdSet.isEmpty()) {
            kAccountOrgOrgIdVAccountOrg = accountOrgService.lambdaQuery()
                    .select(AccountOrg::getOrgId, AccountOrg::getOrgName)
                    .in(AccountOrg::getOrgId, accountOrgOrgIdSet)
                    .list()
                    .parallelStream()
                    .collect(Collectors.toMap(AccountOrg::getOrgId, accountOrg -> accountOrg));
            if (kAccountOrgOrgIdVAccountOrg.size() != accountOrgOrgIdSet.size()) {
                throw new OrgException(EnumAccountStatusCode.ORG_NOT_EXIST_EXCEPTION);
            }
        }
        /* runsix:5.batch save accountIdentifier */
        List<AccountIdentifier> accountIdentifierList = accountInstanceDTOList.parallelStream().map(accountInstanceDTO -> {
            AccountIdentifier accountIdentifier = new AccountIdentifier();
            BeanUtils.copyProperties(accountInstanceDTO, accountIdentifier);
            accountIdentifier.setAccountId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdV(
                    accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
            )));
            return accountIdentifier;
        }).collect(Collectors.toList());
        accountIdentifierService.saveBatch(accountIdentifierList);
        /* runsix:6.batch save accountInstance */
        List<AccountInstance> accountInstanceList = accountInstanceDTOList.parallelStream().map(accountInstanceDTO -> {
            AccountInstance accountInstance = new AccountInstance();
            BeanUtils.copyProperties(accountInstanceDTO, accountInstance);
            accountInstance.setAccountId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdV(
                    accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
            )));
            accountInstance.setPassword(new BCryptPasswordEncoder().encode(accountInstanceDTO.getPassword()));
            return accountInstance;
        }).collect(Collectors.toList());
        accountInstanceService.saveBatch(accountInstanceList);
        /* runsix:7.batch save accountRole if rbacRoleId exist */
        Map<Long, RbacRole> finalKRbacRoleIdVRbacRole = kRbacRoleIdVRbacRole;
        List<AccountRole> accountRoleList = accountInstanceDTOList.parallelStream()
                .filter(accountInstanceDTO ->  Objects.nonNull(accountInstanceDTO.getRbacRoleId()))
                .map(accountInstanceDTO -> AccountRole
                        .builder()
                        .roleId(accountInstanceDTO.getRbacRoleId().toString())
                        .roleName(finalKRbacRoleIdVRbacRole.get(accountInstanceDTO.getRbacRoleId()).getRoleName())
                        .roleCode(finalKRbacRoleIdVRbacRole.get(accountInstanceDTO.getRbacRoleId()).getRoleCode())
                        .principalType(EnumAccountRolePrincipalType.PERSONAL.getCode())
                        .principalId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdV(
                                accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
                        )))
                        .principalName(accountInstanceDTO.getAccountName())
                        .build()
                )
                .collect(Collectors.toList());
        accountRoleService.saveBatch(accountRoleList);
        /* runsix:8.batch save accountGroup if orgId exist */
        Map<String, AccountOrg> finalKAccountOrgOrgIdVAccountOrg = kAccountOrgOrgIdVAccountOrg;
        List<AccountGroup> accountGroupList = accountInstanceDTOList.parallelStream()
                .filter(accountInstanceDTO -> StringUtils.isNotBlank(accountInstanceDTO.getAccountOrgOrgId()))
                .map(accountInstanceDTO -> AccountGroup
                        .builder()
                        .orgId(accountInstanceDTO.getAccountOrgOrgId())
                        .orgName(finalKAccountOrgOrgIdVAccountOrg.get(accountInstanceDTO.getAccountOrgOrgId()).getOrgName())
                        .accountId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdV(
                                accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
                        )))
                        .accountName(accountInstanceDTO.getAccountName())
                        .appId(accountInstanceDTO.getAppId())
                        .build()
                )
                .collect(Collectors.toList());
        accountGroupService.saveBatch(accountGroupList);
    }

    public void batchRegister(MultipartFile file, String appId, Long rbacRoleId, String accountOrgOrgId, String password, String avatar, String source, String phone) {
        List<AccountInstanceDTO> accountInstanceDTOListByFile = getAccountInstanceDTOListByFile(
                file, appId, rbacRoleId, accountOrgOrgId, password, avatar, source, phone);
        batchCreateAccountInstance(accountInstanceDTOListByFile);
    }

    @Transactional(rollbackFor = Exception.class)
    public AccountInstanceVo saveOrUpdateAccountInstance(AccountInstanceResDTO accountInstanceDTO) {
        if(accountInstanceDTO == null){
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_INSTANCE_DTO_CANNOT_BE_NULL);
        }
        //TODO 如果租户id为空，说明是新增不执行查询操作 租户建立与新增用户建立关系？
        if(accountInstanceDTO.getTenantId() != null && accountInstanceDTO.getId() != null){
            Map<String,Object> param = new HashMap<>();
            param.put("accountName",accountInstanceDTO.getAccountName());
            param.put("tenantId",accountInstanceDTO.getTenantId());
            if(accountInstanceService.getAccountInstanceByUserNameAndTenantId(param).size() > 0){
                throw new AccountException(EnumAccountStatusCode.ACCOUNT_EXIST_EXCEPTION);
            }
        }

        /* runsix:2.check whether rbacRoleId exist */
        // 新增角色
//        RbacRole rbacRole = null;
//        if (Objects.nonNull(accountInstanceDTO.getRbacRoleId())) {
//            rbacRole = rbacRoleService.lambdaQuery()
//                    .select(RbacRole::getId, RbacRole::getRoleName, RbacRole::getRoleCode)
//                    .eq(RbacRole::getId, accountInstanceDTO.getRbacRoleId())
//                    .oneOpt()
//                    .orElseThrow(() -> {
//                        throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);
//                    });
//        }
        /* runsix:3.check whether accountOrgOrgId exist */

         // 建立与组织关系
        AccountOrg accountOrg = null;
        // 年代分布 60、70、80
        String chronological = null;
        String shengxiao = null;
        // 星座
        String constellation = null;
        if (StringUtils.isNoneBlank(accountInstanceDTO.getOrgId())) {
            accountOrg = accountOrgService.lambdaQuery()
                    .select(AccountOrg::getOrgId, AccountOrg::getOrgName)
                    .eq(AccountOrg::getOrgId, accountInstanceDTO.getOrgId())
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new OrgException(EnumAccountStatusCode.ORG_NOT_EXIST_EXCEPTION);
                    });
        }
        /* runsix:4.save accountIdentifier */
        // id是否为空 如果不为空说明是修改
        AccountInstance accountInstance = null;
        if(accountInstanceDTO.getBirthday() != null){
            String birthdayYear = DateUtil.format(accountInstanceDTO.getBirthday(), DatePattern.NORM_DATE_PATTERN);
            chronological =  AccountUtil.getChronological(Integer.valueOf(birthdayYear.split("-")[0]));
            constellation = AccountUtil.getConstellation(Integer.valueOf(birthdayYear.split("-")[1]),Integer.valueOf(birthdayYear.split("-")[2]));
            shengxiao = AccountUtil.getYear(Integer.valueOf(birthdayYear.split("-")[0]));
        }
        if(accountInstanceDTO.getId() != null){

            accountIdentifierService.lambdaUpdate()
                    .set(AccountIdentifier::getIdentifier,accountInstanceDTO.getIdCard())
                    .eq(AccountIdentifier::getAccountId,accountInstanceDTO.getAccountId())
                    .update();

//            AccountIdentifier accountIdentifier = accountIdentifierService.lambdaQuery().eq(AccountIdentifier::getAccountId, accountInstanceDTO.getAccountId())
//                    .one();
//            if(accountIdentifier != null){
//                accountIdentifier.setIdentifier(accountInstanceDTO.getIdCard());
//                accountIdentifierService.updateById(accountIdentifier);
//            }
            accountInstanceService.lambdaUpdate()
                    .set(AccountInstance::getPassword,new BCryptPasswordEncoder().encode(accountInstanceDTO.getPassword()))
                    .set(AccountInstance::getPhone,accountInstanceDTO.getPhone())
                    .set(AccountInstance::getStoreId,accountInstanceDTO.getStoreId())
                    .eq(AccountInstance::getAccountId,accountInstanceDTO.getAccountId())
                    .update();

            accountUserInfoService.lambdaUpdate()
                    .set(AccountUserInfo::getSex,accountInstanceDTO.getGender())
                    .set(AccountUserInfo::getName,accountInstanceDTO.getName())
                    .set(AccountUserInfo::getJob,accountInstanceDTO.getJob())
                    .set(AccountUserInfo::getEntryTime,accountInstanceDTO.getEntryTime())
                    .set(AccountUserInfo::getBirthday,accountInstanceDTO.getBirthday())
                    .set(AccountUserInfo::getShengxiao,shengxiao)
                    .set(AccountUserInfo::getChronological,chronological)
                    .set(AccountUserInfo::getConstellation,constellation)
                    .eq(AccountUserInfo::getAccountId,accountInstanceDTO.getAccountId())
                    .update();

            accountGroupService.lambdaUpdate()
                    .set(AccountGroup::getOrgId,accountInstanceDTO.getOrgId())
                    .set(AccountGroup::getOrgName,accountOrg.getOrgName())
                    .eq(AccountGroup::getAccountId,accountInstanceDTO.getAccountId())
                    .update();

            accountInstance = accountInstanceService.lambdaQuery().eq(AccountInstance::getAccountId, accountInstanceDTO.getAccountId())
                    .one();
        }else{
            AccountIdentifier accountIdentifier = new AccountIdentifier();
            BeanUtils.copyProperties(accountInstanceDTO, accountIdentifier);
            accountInstanceDTO.setTenantId(UUID.randomUUID().toString().replace("-",""));
            accountIdentifier.setIdentifier(accountInstanceDTO.getIdCard());
            accountIdentifier.setAccountId(IdWorker.getIdStr());
            accountIdentifierService.save(accountIdentifier);
            /* runsix:5.save accountInstance */
            accountInstance = new AccountInstance();
            BeanUtils.copyProperties(accountInstanceDTO, accountInstance);
            accountInstance.setAccountId(accountIdentifier.getAccountId());
            accountInstance.setPassword(new BCryptPasswordEncoder().encode(accountInstanceDTO.getPassword()));
            /*
             账号类型区分逻辑
             用户登录时，存储redis
             从redis取出当前用户，根据当前账户类型区分 需要新增的用户
             注意：总部端可以申请子账号，及员工账号，员工账号无法登录后台，可以根据前端上送密码区分，有密码则是子账号，无密码则门店端APP员工账号
             */
            //  TODO
            accountInstance.setAccountType(accountInstanceDTO.getAccountType());
            accountInstanceService.save(accountInstance);
            // 账号与租户关系
            accountTenantService.save(
                    AccountTenant
                            .builder()
                            .accountId(accountIdentifier.getAccountId())
                            .tenantId(accountInstanceDTO.getTenantId())
                            .build()
            );
            // 账号与用户关系
            accountUserService.save(
                    AccountUser
                            .builder()
                            .accountId(accountIdentifier.getAccountId())
                            .tentantId(accountInstanceDTO.getTenantId())
                            .appId(accountInstanceDTO.getAppId())
                            .build()
            );
            // 账号与用户详情关系

            accountUserInfoService.save(
                    AccountUserInfo
                            .builder()
                            .accountId(accountIdentifier.getAccountId())
                            .tenantId(accountInstanceDTO.getTenantId())
                            .sex(accountInstanceDTO.getGender())
                            .job(accountInstanceDTO.getJob())
                            .name(accountInstanceDTO.getName())
                            .entryTime(accountInstanceDTO.getEntryTime())
                            .birthday(accountInstanceDTO.getBirthday())
                            .shengxiao(shengxiao)
                            .chronological(chronological)
                            .constellation(constellation)
                            .build()
            );
            if (Objects.nonNull(accountOrg)) {
                accountGroupService.save(
                        AccountGroup
                                .builder()
                                .orgId(accountInstanceDTO.getOrgId())
                                .orgName(accountOrg.getOrgName())
                                .accountId(accountInstance.getAccountId())
                                .accountName(accountInstance.getAccountName())
                                .appId(accountInstanceDTO.getAppId())
                                .build()
                );
            }
        }


        /* runsix:6.save accountRole if rbacRoleId exist */
//        if (Objects.nonNull(rbacRole)) {
//            accountRoleService.save(
//                    AccountRole
//                            .builder()
//                            .roleId(accountInstanceDTO.getRbacRoleId().toString())
//                            .roleName(rbacRole.getRoleName())
//                            .roleCode(rbacRole.getRoleCode())
//                            .principalType(EnumAccountRolePrincipalType.PERSONAL.getCode())
//                            .principalId(accountInstance.getAccountId())
//                            .principalName(accountInstanceDTO.getAccountName())
//                            .build()
//            );
//        }
        /* runsix:7.save accountGroup if orgId exist */

        /* runsix:8.convert entity to vo and return */
        AccountInstanceVo accountInstanceVo = new AccountInstanceVo();
        BeanUtils.copyProperties(accountInstance, accountInstanceVo);
        accountInstanceVo.setAccountId(Long.parseLong(accountInstance.getAccountId()));
        return accountInstanceVo;
    }
}
