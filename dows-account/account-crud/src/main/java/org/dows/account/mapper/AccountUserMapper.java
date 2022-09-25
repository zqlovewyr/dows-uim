package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.entity.AccountUser;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-用户维度信息(AccountUser)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:54
 */
@Mapper
public interface AccountUserMapper extends MybatisCrudMapper<AccountUser> {

}
