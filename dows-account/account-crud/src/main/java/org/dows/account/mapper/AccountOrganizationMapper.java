package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.entity.AccountOrganization;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-组织架构维度信息(AccountOrganization)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:37
 */
@Mapper
public interface AccountOrganizationMapper extends MybatisCrudMapper<AccountOrganization> {

}
