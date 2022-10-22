package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountIdentifier;

/**
 * 账号标识(AccountIdentifier)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:25
 */
@Mapper
public interface AccountIdentifierMapper extends MybatisCrudMapper<AccountIdentifier> {

}

