package org.dows.account.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountInstance;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-实例维度信息(AccountInstance)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:27
 */
@Mapper
public interface AccountInstanceMapper extends MybatisCrudMapper<AccountInstance> {

}
