package org.dows.account.api;

import org.dows.account.api.dto.UserDto;

/**
 * 账号-标识(AccountIdentifier)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:23
 */
public interface AccountIdentifierApi {
    UserDto findByIdentifier(String accountName);

}
