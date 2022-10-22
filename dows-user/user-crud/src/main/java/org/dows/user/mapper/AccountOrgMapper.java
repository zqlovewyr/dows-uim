package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.AccountOrg;

/**
 * 账号-组织架构(AccountOrg)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:27
 */
@Mapper
public interface AccountOrgMapper extends MybatisCrudMapper<AccountOrg> {

}

