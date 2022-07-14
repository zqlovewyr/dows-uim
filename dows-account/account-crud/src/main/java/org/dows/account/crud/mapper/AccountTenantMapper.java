package org.dows.account.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountTenant;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-租户维度信息(AccountTenant)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:49
 */
@Mapper
public interface AccountTenantMapper extends MybatisCrudMapper<AccountTenant> {

}
