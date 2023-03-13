package org.dows.account.biz.util;

import org.dows.account.entity.AccountOrg;
import org.dows.account.vo.AccountOrgVo;

public class AccountOrgUtil {

    public static AccountOrgVo buildVO(AccountOrg entity) {
        return AccountOrgVo.builder()
            .appId(entity.getAppId())
            .descr(entity.getDescr())
            .dt(entity.getDt())
            .expdate(entity.getExpdate())
            .id(entity.getId().toString())
            .indate(entity.getIndate())
            .maxNumber(entity.getMaxNumber())
            .nameLetters(entity.getNameLetters())
            .orgCode(entity.getOrgCode())
            .orgId(entity.getOrgId())
            .orgName(entity.getOrgName())
            .orgType(entity.getOrgType())
            .pid(entity.getPid())
            .profile(entity.getProfile())
            .status(entity.getStatus())
            .tenantId(entity.getTenantId())
            .build();
    }
}
