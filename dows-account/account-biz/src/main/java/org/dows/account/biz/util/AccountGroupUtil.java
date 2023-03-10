package org.dows.account.biz.util;

import org.dows.account.entity.AccountGroup;
import org.dows.account.vo.AccountGroupVo;

/***/
public class AccountGroupUtil {

    public static AccountGroupVo buildVo(AccountGroup entity) {
        return AccountGroupVo.builder()
            .accountId(entity.getAccountId())
            .accountName(entity.getAccountName())
            .appId(entity.getAppId())
            .deleted(entity.getAccountName())
            .dt(entity.getDt())
            .id(entity.getId())
            .orgId(entity.getOrgId())
            .orgName(entity.getOrgName())
            .tenantId(entity.getTenantId())
            .userId(entity.getUserId())
            .build();
    }
}
