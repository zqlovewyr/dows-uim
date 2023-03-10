package org.dows.account.api;

import org.dows.account.vo.AccountIdentifierSearchVO;
import org.dows.account.vo.AccountIdentifierVO;

import java.util.List;
import java.util.Set;

/**
 * 账号-标识(AccountIdentifier)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:23
 */
public interface AccountIdentifierApi {
    /**
     * 获取账号，标识
     */
    List<AccountIdentifierVO> searchIdentifier(AccountIdentifierSearchVO searchVO);

    /**
     * 批量移除(根据账号ID集合)
     */
    boolean removeByAccountIds(Set<String> accountIds);
}
