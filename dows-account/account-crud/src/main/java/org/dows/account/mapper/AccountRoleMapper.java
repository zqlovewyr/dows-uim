package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountRole;

/**
 * 账号-角色(AccountRole)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:29
 */
@Mapper
public interface AccountRoleMapper extends MybatisCrudMapper<AccountRole> {

}

