package org.dows.account.api;

import org.dows.account.vo.AccountVo;

import java.util.List;

/**
 * 账号-用户维度信息(AccountUser)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:36
 */
public interface AccountUserApi {

    /**
     * 根据accountId查询
     * @param accountId
     * @return
     */
    AccountVo getInfoByAccountId(String accountId);

    /**
     * 根据多个accountId查询
     * @param accountIds
     * @return
     */
    List<AccountVo> getInfoByAccountIds(String[] accountIds);

    /**
     * 根据账号名称及类型查询
     * @param accountName
     * @param accountType
     * @return
     */
    AccountVo queryAccountVoByAccountName(String accountName,Integer accountType);
}
