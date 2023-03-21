package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.entity.AccountAddress;
import org.dows.account.entity.AccountUser;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-地址(实名认证后)(AccountAddress)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@Mapper
public interface AccountAddressMapper extends MybatisCrudMapper<AccountAddress> {

}

