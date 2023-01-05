package org.dows.account.api;

import org.dows.account.dto.AccountOrgGroupDTO;
import java.util.List;

/**
 * 账号-账号组维度信息(AccountGroup)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:22
 */
public interface AccountGroupApi {
    void batchInsertGroup(List<AccountOrgGroupDTO> accountOrgGroupDTOS);
}
