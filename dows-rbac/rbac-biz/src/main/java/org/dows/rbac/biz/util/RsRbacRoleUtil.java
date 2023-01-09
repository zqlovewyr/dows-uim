package org.dows.rbac.biz.util;

import org.dows.rbac.api.vo.RbacRoleVO;
import org.dows.rbac.entity.RbacRole;

import java.util.Objects;

/**
 * @author runsix
 */
public class RsRbacRoleUtil {
    public static RbacRoleVO rbacRole2VO(RbacRole rbacRole) {
        if (Objects.isNull(rbacRole)) {
            return null;
        } else {
            return RbacRoleVO
                    .builder()
                    .id(rbacRole.getId())
                    .pid(rbacRole.getPid())
                    .roleName(rbacRole.getRoleName())
                    .nameLetters(rbacRole.getNameLetters())
                    .roleCode(rbacRole.getRoleCode())
                    .roleIcon(rbacRole.getRoleIcon())
                    .descr(rbacRole.getDescr())
                    .appId(rbacRole.getAppId())
                    .tenantId(rbacRole.getTenantId())
                    .inherit(rbacRole.getInherit())
                    .deleted(rbacRole.getDeleted())
                    .dt(rbacRole.getDt())
                    .build();
        }
    }

    public static RbacRole vo2RbacRole(RbacRoleVO rbacRoleVO) {
        if (Objects.isNull(rbacRoleVO)) {
            return null;
        } else {
            return RbacRole
                    .builder()
                    .id(rbacRoleVO.getId())
                    .pid(rbacRoleVO.getPid())
                    .roleName(rbacRoleVO.getRoleName())
                    .nameLetters(rbacRoleVO.getNameLetters())
                    .roleCode(rbacRoleVO.getRoleCode())
                    .roleIcon(rbacRoleVO.getRoleIcon())
                    .descr(rbacRoleVO.getDescr())
                    .appId(rbacRoleVO.getAppId())
                    .tenantId(rbacRoleVO.getTenantId())
                    .inherit(rbacRoleVO.getInherit())
                    .deleted(rbacRoleVO.getDeleted())
                    .dt(rbacRoleVO.getDt())
                    .build();
        }
    }
}
