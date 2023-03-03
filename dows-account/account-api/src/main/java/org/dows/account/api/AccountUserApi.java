package org.dows.account.api;

import org.dows.account.dto.AccountOrgDTO;
import org.dows.account.dto.AccountUserDTO;
import org.dows.framework.api.Response;

/**
 * 账号-用户维度信息(AccountUser)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:36
 */
public interface AccountUserApi {
    /**
     * 插入 账号与用户关系
     *
     * @param accountUserDTO
     */
    Response<Long> createAccountUser(AccountUserDTO accountUserDTO);

    /**
     * 根据账户id 获取用户信息
     *
     * @param accountId
     */
    Response getUserByAccountId(String accountId);
}
