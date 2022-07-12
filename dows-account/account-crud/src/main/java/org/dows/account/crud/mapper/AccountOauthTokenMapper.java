package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountOauthToken;

/**
 * 账号-第三方账号授权(AccountOauthToken)
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:29
 */
@Mapper
public interface AccountOauthTokenMapper extends BaseMapper<AccountOauthToken> {

}
