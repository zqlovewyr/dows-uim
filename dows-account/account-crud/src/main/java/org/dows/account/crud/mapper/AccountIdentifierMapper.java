package org.dows.account.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountIdentifier;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-标识(AccountIdentifier)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:23
 */
@Mapper
public interface AccountIdentifierMapper extends MybatisCrudMapper<AccountIdentifier> {

}
