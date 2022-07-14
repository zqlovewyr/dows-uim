package org.dows.account.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountGroup;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-账号组维度信息(AccountGroup)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:19
 */
@Mapper
public interface AccountGroupMapper extends MybatisCrudMapper<AccountGroup> {

}
