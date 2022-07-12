package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.dows.account.crud.entity.AccountIdentifier;

/**
 * 账号-标识(AccountIdentifier)
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:24
 */
@Mapper
public interface AccountIdentifierMapper extends BaseMapper<AccountIdentifier> {

    @Update("@AccountIdentifierMapper.updateAccountIdentifier")
    int updateAccountIdentifier(AccountIdentifier accountIdentifier);
}
