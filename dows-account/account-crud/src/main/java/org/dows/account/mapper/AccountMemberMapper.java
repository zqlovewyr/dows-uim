package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountMember;

/**
 * 账号-会员(AccountMember)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Mapper
public interface AccountMemberMapper extends MybatisCrudMapper<AccountMember> {

}

