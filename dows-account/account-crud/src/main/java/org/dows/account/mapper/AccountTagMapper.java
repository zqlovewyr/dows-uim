package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.entity.AccountTag;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-标签维度信息(AccountTag)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:43
 */
@Mapper
public interface AccountTagMapper extends MybatisCrudMapper<AccountTag> {

}
