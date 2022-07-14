package org.dows.account.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountRole;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-主体角色维度信息(AccountRole)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:41
 */
@Mapper
public interface AccountRoleMapper extends MybatisCrudMapper<AccountRole> {

}
