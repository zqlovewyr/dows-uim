package org.dows.account.crud.service.impl;


import org.dows.account.crud.entity.AccountOrganization;
import org.dows.account.crud.mapper.AccountOrganizationMapper;
import org.dows.account.crud.service.AccountOrganizationService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 账号-组织架构维度信息(AccountOrganization)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:36
 */
@Service
public class AccountOrganizationServiceImpl extends MybatisCrudServiceImpl<AccountOrganizationMapper, AccountOrganization> implements AccountOrganizationService {

}
