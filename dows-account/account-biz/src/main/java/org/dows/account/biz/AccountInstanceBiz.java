package org.dows.account.biz;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dows.account.api.AccountInstanceApi;
import org.dows.account.biz.constant.AccountConstant;
import org.dows.account.biz.constant.BaseConstant;
import org.dows.account.biz.enums.EnumAccountRolePrincipalType;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import org.dows.account.biz.exception.OrgException;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.biz.util.JwtUtil;
import org.dows.account.biz.util.ReflectUtil;
import org.dows.account.dto.AccountInstanceDTO;
import org.dows.account.entity.*;
import org.dows.account.service.*;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.Response;
import org.dows.rbac.api.RbacRoleApi;
import org.dows.rbac.vo.RbacRoleVo;
import org.dows.user.api.api.UserInstanceApi;
import org.dows.user.api.dto.UserInstanceDTO;
import org.dows.user.api.vo.UserInstanceVo;
import org.dows.user.entity.UserInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
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
public class AccountInstanceBiz implements AccountInstanceApi {
    private final AccountInstanceService accountInstanceService;
    private final AccountUserService accountUserService;
    private final AccountIdentifierService accountIdentifierService;
    private final RbacRoleApi rbacRoleApi;
    private final AccountRoleService accountRoleService;
    private final AccountOrgService accountOrgService;
    private final AccountGroupService accountGroupService;
    private final UserInstanceApi userInstanceApi;
    private final AccountGroupInfoService accountGroupInfoService;

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
    public Response<AccountInstanceVo> createAccountInstance1(AccountInstanceDTO accountInstanceDTO) {
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
        RbacRoleVo rbacRoleVO = null;
        if (Objects.nonNull(accountInstanceDTO.getRbacRoleId())) {
            Response<RbacRoleVo> rbacRoleVOResponse = rbacRoleApi.getById(String.valueOf(accountInstanceDTO.getRbacRoleId()));
            rbacRoleVO = rbacRoleVOResponse.getData();
        }
        /* runsix:3.check whether accountOrgOrgId exist */
        AccountOrg accountOrg = null;
        if (StringUtils.isNotBlank(accountInstanceDTO.getAccountOrgOrgId())) {
            accountOrg = accountOrgService.lambdaQuery()
                    .select(AccountOrg::getId, AccountOrg::getOrgName)
                    .eq(AccountOrg::getId, accountInstanceDTO.getAccountOrgOrgId())
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new OrgException(EnumAccountStatusCode.ORG_NOT_EXIST_EXCEPTION);
                    });
        }
        /* runsix:5.save accountInstance */
        AccountInstance accountInstance = new AccountInstance();
        BeanUtils.copyProperties(accountInstanceDTO, accountInstance);
        accountInstance.setAccountId(IdWorker.getIdStr());
        accountInstanceService.save(accountInstance);

        /* runsix:4.save accountIdentifier */
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        BeanUtils.copyProperties(accountInstanceDTO, accountIdentifier);
        accountIdentifier.setAccountId(accountInstance.getId().toString());
        accountIdentifierService.save(accountIdentifier);

        /* runsix:6.save accountRole if rbacRoleId exist */
        if (Objects.nonNull(rbacRoleVO)) {
            accountRoleService.save(
                    AccountRole
                            .builder()
                            .roleId(accountInstanceDTO.getRbacRoleId().toString())
                            .roleName(rbacRoleVO.getRoleName())
                            .roleCode(rbacRoleVO.getRoleCode())
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
        return Response.ok(accountInstanceVo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Response<AccountInstanceVo> createAccountInstance(AccountInstanceDTO accountInstanceDTO) {
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
        RbacRoleVo rbacRoleVO = null;
        if (Objects.nonNull(accountInstanceDTO.getRbacRoleId())) {
            Response<RbacRoleVo> rbacRoleVOResponse = rbacRoleApi.getById(String.valueOf(accountInstanceDTO.getRbacRoleId()));
            rbacRoleVO = rbacRoleVOResponse.getData();
        }
        /* runsix:3.check whether accountOrgOrgId exist */
        AccountOrg accountOrg = null;
        if (StringUtils.isNotBlank(accountInstanceDTO.getAccountOrgOrgId())) {
            accountOrg = accountOrgService.lambdaQuery()
                    .select(AccountOrg::getId, AccountOrg::getId)
                    .eq(AccountOrg::getId, Long.valueOf(accountInstanceDTO.getAccountOrgOrgId()))
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new OrgException(EnumAccountStatusCode.ORG_NOT_EXIST_EXCEPTION);
                    });
        }
        /* runsix:5.save accountInstance */
        AccountInstance accountInstance = new AccountInstance();
        BeanUtils.copyProperties(accountInstanceDTO, accountInstance);
        accountInstance.setAccountId(IdWorker.getIdStr());
        if (StringUtils.isNotEmpty(accountInstanceDTO.getPassword())) {
            accountInstance.setPassword("123456");
        }
        accountInstanceService.save(accountInstance);

        /* runsix:4.save accountIdentifier */
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        BeanUtils.copyProperties(accountInstanceDTO, accountIdentifier);
        accountIdentifier.setAccountId(accountInstance.getId().toString());
        accountIdentifierService.save(accountIdentifier);

        /* runsix:6.save accountRole if rbacRoleId exist */
        if (Objects.nonNull(rbacRoleVO)) {
            AccountRole role = new AccountRole();
            BeanUtils.copyProperties(rbacRoleVO, role, new String[]{"id"});
            role.setRoleId(accountInstanceDTO.getRbacRoleId().toString());
            role.setPrincipalType(accountInstanceDTO.getPrincipalType());
            role.setPrincipalId(accountInstance.getId().toString());
            if (StringUtils.isNotEmpty(accountInstanceDTO.getAccountName())) {
                role.setPrincipalName(accountInstanceDTO.getAccountName());
            }
            role.setStatus(1);
            if (StringUtils.isNotEmpty(accountInstanceDTO.getTenantId())) {
                role.setTenantId(accountInstanceDTO.getTenantId());
            }
            accountRoleService.save(role);
        }
        /* runsix:7.save accountGroup if orgId exist */
        if (Objects.nonNull(accountOrg)) {
            AccountGroup accountGroup = new AccountGroup();
            accountGroup.setOrgId(accountInstanceDTO.getAccountOrgOrgId());
            accountGroup.setOrgName(accountOrg.getOrgName());
            accountGroup.setAccountId(accountInstance.getId().toString());
            if (StringUtils.isNotEmpty(accountInstance.getAccountName())) {
                accountGroup.setAccountName(accountInstance.getAccountName());
            }
            if (StringUtils.isNotEmpty(accountInstanceDTO.getUserId())) {
                accountGroup.setUserId(accountInstanceDTO.getUserId());
            }
            accountGroup.setAppId(accountInstanceDTO.getAppId());
            accountGroup.setTenantId(accountInstanceDTO.getTenantId());
            accountGroupService.save(accountGroup);
        }
        /* runsix:8.convert entity to vo and return */
        AccountInstanceVo accountInstanceVo = new AccountInstanceVo();
        BeanUtils.copyProperties(accountInstance, accountInstanceVo);
        accountInstanceVo.setAccountId(Long.parseLong(accountInstance.getAccountId()));
        return Response.ok(accountInstanceVo);
    }

    public Response<List<AccountInstanceDTO>> getAccountInstanceDTOListByFile(MultipartFile file, String appId, Long rbacRoleId, String accountOrgOrgId, String password, String avatar, String source, String phone) {
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
        return Response.ok(accountInstanceDTOList);
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
            kIdentifierAppIdVAccountId.put(getKeyOfkIdentifierAppIdV(identifier, appId), IdWorker.getIdStr());
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
        List<String> rbacRoleIdList = accountInstanceDTOList.parallelStream()
                .map(accountInstanceDTO -> accountInstanceDTO.getRbacRoleId().toString())
                .collect(Collectors.toList());
        Map<Long, RbacRoleVo> kRbacRoleIdVRbacRoleVOMap = new ConcurrentHashMap<>();
        if (!rbacRoleIdList.isEmpty()) {
            /*           Response<List<RbacRoleVO>> rbacRoleVOListResponse = rbacRoleApi.getByIdList(rbacRoleIdList);*/
            Response<List<RbacRoleVo>> rbacRoleVOListResponse = new Response<List<RbacRoleVo>>();
            List<RbacRoleVo> rbacRoleVOList = rbacRoleVOListResponse.getData();
            kRbacRoleIdVRbacRoleVOMap = rbacRoleVOList
                    .parallelStream()
                    .collect(Collectors.toMap(RbacRoleVo::getId, a -> a));
            if (kRbacRoleIdVRbacRoleVOMap.size() != rbacRoleIdList.size()) {
                /*            throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);*/
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
        Map<Long, RbacRoleVo> finalKRbacRoleIdVRbacRoleVO = kRbacRoleIdVRbacRoleVOMap;
        List<AccountRole> accountRoleList = accountInstanceDTOList.parallelStream()
                .filter(accountInstanceDTO -> Objects.nonNull(accountInstanceDTO.getRbacRoleId()))
                .map(accountInstanceDTO -> AccountRole
                        .builder()
                        .roleId(accountInstanceDTO.getRbacRoleId().toString())
                        .roleName(finalKRbacRoleIdVRbacRoleVO.get(accountInstanceDTO.getRbacRoleId()).getRoleName())
                        .roleCode(finalKRbacRoleIdVRbacRoleVO.get(accountInstanceDTO.getRbacRoleId()).getRoleCode())
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
        Response<List<AccountInstanceDTO>> accountInstanceDTOListByFile = getAccountInstanceDTOListByFile(
                file, appId, rbacRoleId, accountOrgOrgId, password, avatar, source, phone);
        batchCreateAccountInstance(accountInstanceDTOListByFile.getData());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Map<String, Object>> login(AccountInstanceDTO accountInstanceDTO) {
        //1、获取账户是否存在
        LambdaQueryWrapper<AccountInstance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(accountInstanceDTO.getAccountName()), AccountInstance::getAccountName, accountInstanceDTO.getAccountName())
                .eq(StringUtils.isNotEmpty(accountInstanceDTO.getPassword()), AccountInstance::getPassword, accountInstanceDTO.getPassword());
        AccountInstance accountInstance = accountInstanceService.getOne(queryWrapper);
        if (accountInstance == null) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_NOT_EXIST_EXCEPTION);
        }
        //2、判断账户状态
        if (accountInstance.getStatus() == AccountConstant.STATUS_N) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_STATUS_INVALID_EXCEPTION);
        }
        //3、判断账户是否在有效期内
        if (accountInstance.getIndate() != null && accountInstance.getExpdate() != null) {
            //判断当前登录时间是否有效期内
            Date now = new Date();
            if (accountInstance.getIndate().getTime() >= now.getTime() || accountInstance.getExpdate().getTime() <= now.getTime()) {
                throw new AccountException(EnumAccountStatusCode.ACCOUNT_NOT_IN_VALIDITY_EXCEPTION);
            }
        }
        //4、使用RSA解密
        if (!accountInstance.getPassword().equals(accountInstanceDTO.getPassword())) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_PASSWORD_NOT_MATCH_EXCEPTION);
        }
        //5、编辑jwt加密
        Map<String, Object> claim = new HashMap<>();
        claim.put("accountId", accountInstance.getAccountId());
        Date expireDate = new Date(System.currentTimeMillis() + 100 * 60 * 60 * 1000);
        String token = JwtUtil.createJWT(claim, expireDate, BaseConstant.PROPERTIES_JWT_KEY);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("accountId", accountInstance.getAccountId());
        map.put("name", accountInstance.getAccountName());

        //判断用户角色
        LambdaQueryWrapper<AccountRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(accountInstance.getAccountId()), AccountRole::getPrincipalId, accountInstance.getAccountId());
        AccountRole accountRole = accountRoleService.getOne(wrapper);
        if (accountRole.getRoleId().equals("0")) {
            map.put("role", "superAdmin");
        } else if (accountRole.getRoleId().equals("1")) {
            map.put("role", "admin");
        } else if (accountRole.getRoleId().equals("2")) {
            map.put("role", "member");
        }
        return Response.ok(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<IPage<AccountInstanceVo>> customAccountInstanceList(AccountInstanceDTO accountInstanceDTO) {
        //1.1、获取角色Id获取对应账号Id
        Set<String> accountIds = new HashSet<>();
        if (accountInstanceDTO.getRoleId() != null) {
            List<AccountRole> accountRoleList = accountRoleService.lambdaQuery()
                    .select(AccountRole::getPrincipalId)
                    .eq(AccountRole::getRoleId, accountInstanceDTO.getRoleId())
                    .eq(AccountRole::getPrincipalType, EnumAccountRolePrincipalType.PERSONAL.getCode())
                    .eq(AccountRole::getDeleted, false)
                    .list();
            if (accountRoleList != null && accountRoleList.size() > 0) {
                accountRoleList.forEach(accountRole -> {
                    accountIds.add(accountRole.getPrincipalId());
                });
            }
        }


        //1.2、获取角色名称获取对应账号Id
        if (StringUtils.isNotEmpty(accountInstanceDTO.getRoleName())) {
            List<AccountRole> accountRoleList = accountRoleService.lambdaQuery()
                    .select(AccountRole::getPrincipalId)
                    .eq(AccountRole::getRoleName, accountInstanceDTO.getRoleName())
                    .eq(AccountRole::getPrincipalType, EnumAccountRolePrincipalType.PERSONAL.getCode())
                    .eq(AccountRole::getDeleted, false)
                    .list();
            if (accountRoleList != null && accountRoleList.size() > 0) {
                accountRoleList.forEach(accountRole -> {
                    accountIds.add(accountRole.getPrincipalId());
                });
            }
        }


        //2、根据姓名、性别查询对应用户
        UserInstanceDTO dto = new UserInstanceDTO();
        if (StringUtils.isNotEmpty(accountInstanceDTO.getUserName())) {
            dto.setName(accountInstanceDTO.getUserName());
        }
        if (StringUtils.isNotEmpty(accountInstanceDTO.getGender())) {
            dto.setGender(accountInstanceDTO.getGender());
        }
        if (!ReflectUtil.isObjectNull(dto) && dto != null) {
            List<UserInstanceVo> instanceList = userInstanceApi.getUserInstanceList(dto).getData();
            if (instanceList != null && instanceList.size() > 0) {
                instanceList.forEach(model -> {
                    AccountUser user = accountUserService.lambdaQuery()
                            .eq(AccountUser::getUserId, model.getId())
                            .one();
                    if (user != null) {
                        accountIds.add(user.getAccountId());
                    }
                });
            }
        }

        //3、根据姓名、手机号查询用户之姓名
        UserInstanceDTO dtoName = new UserInstanceDTO();
        if(StringUtils.isNotEmpty(accountInstanceDTO.getAccountNamePhone())){
            dtoName.setName(accountInstanceDTO.getAccountNamePhone());
        }
        if (!ReflectUtil.isObjectNull(dtoName) && dtoName != null) {
            List<UserInstanceVo> instanceList = userInstanceApi.getUserInstanceList(dtoName).getData();
            if (instanceList != null && instanceList.size() > 0) {
                instanceList.forEach(model -> {
                    AccountUser user = accountUserService.lambdaQuery()
                            .eq(AccountUser::getUserId, model.getId())
                            .one();
                    if (user != null) {
                        accountIds.add(user.getAccountId());
                    }
                });
            }
        }
        //3、根据姓名、手机号查询用户之手机号
        UserInstanceDTO dtoPhone = new UserInstanceDTO();
        if(StringUtils.isNotEmpty(accountInstanceDTO.getAccountNamePhone())){
            dtoPhone.setPhone(accountInstanceDTO.getAccountNamePhone());
        }
        if (!ReflectUtil.isObjectNull(dtoPhone) && dtoPhone != null) {
            List<UserInstanceVo> instanceList = userInstanceApi.getUserInstanceList(dtoPhone).getData();
            if (instanceList != null && instanceList.size() > 0) {
                instanceList.forEach(model -> {
                    AccountUser user = accountUserService.lambdaQuery()
                            .eq(AccountUser::getUserId, model.getId())
                            .one();
                    if (user != null) {
                        accountIds.add(user.getAccountId());
                    }
                });
            }
        }

        //4、根据所属机构名称查询账户
        if (StringUtils.isNotEmpty(accountInstanceDTO.getOrgName())) {
            //4、1 获取机构id
            List<AccountGroupInfo> infoList = accountGroupInfoService.lambdaQuery()
                    .like(AccountGroupInfo::getOrgName, accountInstanceDTO.getOrgName())
                    .list();
            //4.2、获取机构下的账户id集合
            infoList.forEach(model -> {
                List<AccountGroup> groupList = accountGroupService.lambdaQuery().eq(AccountGroup::getOrgId, model.getOrgId()).eq(AccountGroup::getDeleted, false).list();
                if (groupList != null && groupList.size() > 0) {
                    groupList.forEach(group -> {
                        accountIds.add(group.getAccountId());
                    });
                }
            });
        }

        //5、根据组别ID查询账户
        if (StringUtils.isNotEmpty(accountInstanceDTO.getTeamName())) {
            //5、1 获取机构id
            List<AccountOrg> orgList = accountOrgService.lambdaQuery()
                    .like(AccountOrg::getOrgName, accountInstanceDTO.getTeamName())
                    .list();
            //5.2、获取机构下的账户id集合
            orgList.forEach(model -> {
                List<AccountGroup> groupList = accountGroupService.lambdaQuery().eq(AccountGroup::getOrgId, model.getId()).eq(AccountGroup::getDeleted, false).list();
                if (groupList != null && groupList.size() > 0) {
                    groupList.forEach(group -> {
                        accountIds.add(group.getAccountId());
                    });
                }
            });
        }

        //6、查询列表
        LambdaQueryWrapper<AccountInstance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(accountInstanceDTO.getAccountId()), AccountInstance::getAccountId, accountInstanceDTO.getAccountId())
                .in(accountIds != null && accountIds.size() > 0, AccountInstance::getId, accountIds)
                .like(StringUtils.isNotEmpty(accountInstanceDTO.getAccountName()), AccountInstance::getAccountName, accountInstanceDTO.getAccountName())
                .eq(StringUtils.isNotEmpty(accountInstanceDTO.getSource()), AccountInstance::getSource, accountInstanceDTO.getSource())
                .like(StringUtils.isNotEmpty(accountInstanceDTO.getPhone()), AccountInstance::getPhone, accountInstanceDTO.getPhone())
                .eq(StringUtils.isNotEmpty(accountInstanceDTO.getAppId()), AccountInstance::getAppId, accountInstanceDTO.getAppId())
                .eq(accountInstanceDTO.getStatus() != null, AccountInstance::getStatus, accountInstanceDTO.getStatus())
                .eq(accountInstanceDTO.getDt() != null, AccountInstance::getDt, accountInstanceDTO.getDt())
                .gt(accountInstanceDTO.getStartTime() != null, AccountInstance::getDt, accountInstanceDTO.getStartTime())
                .lt(accountInstanceDTO.getEndTime() != null, AccountInstance::getDt, accountInstanceDTO.getEndTime())
                .orderByDesc(AccountInstance::getDt);
        Page<AccountInstance> page = new Page<>(accountInstanceDTO.getPageNo(), accountInstanceDTO.getPageSize());
        IPage<AccountInstance> instancePage = accountInstanceService.page(page, queryWrapper);
        //7、属性赋值
        List<AccountInstanceVo> voList = new ArrayList<>();
        if (instancePage.getRecords() != null && instancePage.getRecords().size() > 0) {
            instancePage.getRecords().forEach(model -> {
                AccountInstanceVo vo = new AccountInstanceVo();
                BeanUtils.copyProperties(model, vo);
                //7.1、设置姓名、性别
                //7.1、1 根据accountId获取userId
                AccountUser user = accountUserService.lambdaQuery()
                        .eq(AccountUser::getAccountId, model.getId().toString())
                        .one();
                if (!ReflectUtil.isObjectNull(user)) {
                    UserInstanceVo instance = userInstanceApi.getUserInstanceById(Long.valueOf(user.getUserId())).getData();
                    if (!ReflectUtil.isObjectNull(instance)) {
                        if (StringUtils.isNotEmpty(instance.getName())) {
                            vo.setName(instance.getName());
                        }
                        if (StringUtils.isNotEmpty(instance.getGender())) {
                            vo.setGender(instance.getGender());
                        }
                    }
                }
                //7.1.2、设置机构信息
                AccountGroup group = accountGroupService.lambdaQuery()
                        .eq(AccountGroup::getAccountId, model.getId().toString())
                        .one();
                if (!ReflectUtil.isObjectNull(group) && group != null) {
                    if (StringUtils.isNotEmpty(group.getOrgName())) {
                        vo.setOrgName(group.getOrgName());
                    }
                    AccountGroupInfo groupInfo = accountGroupInfoService.lambdaQuery()
                            .eq(AccountGroupInfo::getAccountId, group.getOrgId())
                            .one();
                    if (!ReflectUtil.isObjectNull(groupInfo) && groupInfo != null) {
                        if (StringUtils.isNotEmpty(groupInfo.getGroupInfoId())) {
                            vo.setGroupInfoId(groupInfo.getGroupInfoId());
                        }
                    }
                }
                //7.1.3 设置角色信息
                AccountRole accountRole = accountRoleService.lambdaQuery()
                        .eq(AccountRole::getPrincipalId, model.getId().toString())
                        .one();
                if (!ReflectUtil.isObjectNull(accountRole) && accountRole != null) {
                    if (StringUtils.isNotEmpty(accountRole.getRoleName())) {
                        vo.setRoleName(accountRole.getRoleName());
                    }
                }
                voList.add(vo);
            });
        }
        //8、复制
        IPage<AccountInstanceVo> voPage = new Page<>();
        BeanUtils.copyProperties(instancePage, voPage, new String[]{"records"});
        voPage.setRecords(voList);
        return Response.ok(voPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAccountInstanceById(AccountInstanceDTO accountInstanceDTO) {
        //1、修改账号-实例
        AccountInstance account = new AccountInstance();
        account.setPhone(accountInstanceDTO.getPhone());
        account.setPassword(accountInstanceDTO.getPassword());
        account.setId(Long.valueOf(accountInstanceDTO.getId()));
        boolean flag1 = accountInstanceService.updateById(account);
        if (flag1 == false) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_UPDATE_FAIL_EXCEPTION);
        }
        //2、通过账号ID找到用户ID
        AccountUser accountUser = accountUserService.lambdaQuery()
                .eq(AccountUser::getAccountId, account.getId().toString())
                .one();
        //2、修改用户-实例
        UserInstanceDTO user = new UserInstanceDTO();
        user.setName(accountInstanceDTO.getUserName());
        user.setGender(accountInstanceDTO.getGender());
        user.setId(Long.valueOf(accountUser.getUserId()));
        userInstanceApi.updateUserInstance(user).getData();
    }

    @Override
    public Response<List<AccountInstanceVo>> getAccountInstanceList(AccountInstanceDTO accountInstanceDTO) {
        List<AccountInstance> voList = accountInstanceService.lambdaQuery()
                .like(StringUtils.isNotEmpty(accountInstanceDTO.getAccountId()), AccountInstance::getAccountId, accountInstanceDTO.getAccountId())
                .like(StringUtils.isNotEmpty(accountInstanceDTO.getAccountName()), AccountInstance::getAccountName, accountInstanceDTO.getAccountName())
                .eq(StringUtils.isNotEmpty(accountInstanceDTO.getSource()), AccountInstance::getSource, accountInstanceDTO.getSource())
                .like(StringUtils.isNotEmpty(accountInstanceDTO.getPhone()), AccountInstance::getPhone, accountInstanceDTO.getPhone())
                .eq(StringUtils.isNotEmpty(accountInstanceDTO.getAppId()), AccountInstance::getAppId, accountInstanceDTO.getAppId())
                .eq(accountInstanceDTO.getStatus() != null, AccountInstance::getStatus, accountInstanceDTO.getStatus())
                .eq(accountInstanceDTO.getDt() != null, AccountInstance::getDt, accountInstanceDTO.getDt())
                .gt(accountInstanceDTO.getStartTime() != null, AccountInstance::getDt, accountInstanceDTO.getStartTime())
                .lt(accountInstanceDTO.getEndTime() != null, AccountInstance::getDt, accountInstanceDTO.getEndTime())
                .orderByDesc(AccountInstance::getDt).list();
        //复制属性
        List<AccountInstanceVo> list = new ArrayList<>();
        if (voList != null && voList.size() > 0) {
            voList.forEach(vo -> {
                AccountInstanceVo model = new AccountInstanceVo();
                BeanUtils.copyProperties(vo, model);
                list.add(model);
            });
        }
        return Response.ok(list);
    }

    @Override
    public Response<Boolean> deleteAccountInstanceById(Long id) {
        LambdaUpdateWrapper<AccountInstance> instanceWrapper = Wrappers.lambdaUpdate(AccountInstance.class);
        instanceWrapper.set(AccountInstance::getDeleted, true)
                .eq(AccountInstance::getId, id);
        boolean flag = accountInstanceService.update(instanceWrapper);
        return Response.ok(flag);
    }

    @Override
    public void batchDeleteAccountInstances(List<String> ids) {
        ids.forEach(id -> {
            LambdaUpdateWrapper<AccountInstance> instanceWrapper = Wrappers.lambdaUpdate(AccountInstance.class);
            instanceWrapper.set(AccountInstance::getDeleted, true)
                    .eq(AccountInstance::getId, id);
            accountInstanceService.update(instanceWrapper);
        });
    }

    @Override
    public Response<Boolean> resetPwd(AccountInstanceDTO accountInstanceDTO) {
        //1、获取对应账户名的账户
        AccountInstance accountInstance = accountInstanceService.lambdaQuery()
                .eq(AccountInstance::getAccountName, accountInstanceDTO.getAccountName())
                .one();
        if (accountInstance == null) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_NOT_EXIST_EXCEPTION);
        }
        //2、更改密码
        LambdaUpdateWrapper<AccountInstance> instanceWrapper = Wrappers.lambdaUpdate(AccountInstance.class);
        instanceWrapper.set(AccountInstance::getPassword, accountInstanceDTO.getPassword())
                .eq(AccountInstance::getId, accountInstance.getId());
        return Response.ok(accountInstanceService.update(instanceWrapper));
    }

    @Override
    public Response<AccountInstanceVo> getAccountInstanceById(String id) {
        //1、获取账户实例
        AccountInstance accountInstance = accountInstanceService.lambdaQuery()
                .eq(AccountInstance::getId, Long.valueOf(id))
                .one();
        if (accountInstance == null) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_NOT_EXIST_EXCEPTION);
        }
        AccountInstanceVo model = new AccountInstanceVo().builder().build()
                .setId(Long.valueOf(accountInstance.getId()))
                .setAccountName(accountInstance.getAccountName())
                .setAccountPwd(accountInstance.getPassword())
                .setPhone(accountInstance.getPhone());
        //2、通过账户和用户关联表找到对应的用户ID
        AccountUser accountUser = accountUserService.lambdaQuery()
                .eq(AccountUser::getAccountId, id)
                .one();
        if (accountUser != null) {
            UserInstanceVo vo = userInstanceApi.getUserInstanceById(Long.valueOf(accountUser.getUserId())).getData();
            BeanUtils.copyProperties(vo, model);
        }
        //4、获取角色实例
        AccountRole accountRole = accountRoleService.lambdaQuery()
                .eq(AccountRole::getPrincipalId, id)
                .one();
        if (accountRole != null) {
            model.setRoleName(accountRole.getRoleName());
        }
        //5、获取机构实例
        AccountGroup accountGroup = accountGroupService.lambdaQuery()
                .eq(AccountGroup::getAccountId, id)
                .one();
        if (accountGroup != null) {
            model.setOrgName(accountGroup.getOrgName());
        }
        return Response.ok(model);
    }
}
