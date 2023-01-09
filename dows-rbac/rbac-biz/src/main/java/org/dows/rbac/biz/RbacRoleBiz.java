package org.dows.rbac.biz;

import lombok.RequiredArgsConstructor;
import org.dows.rbac.api.vo.RbacRoleVO;
import org.dows.rbac.biz.util.RsRbacRoleUtil;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.service.RbacRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RBAC-角色(RbacRole)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:19
 */
@RequiredArgsConstructor
@Service
public class RbacRoleBiz {
    private final RbacRoleService rbacRoleService;

    public RbacRoleVO getById(String id) {
        return RsRbacRoleUtil.rbacRole2VO(
                rbacRoleService.lambdaQuery()
                        .eq(RbacRole::getId, id)
                        .one()
        );
    }

    public List<RbacRoleVO> getByIdList(List<String> idList) {
        return rbacRoleService.lambdaQuery()
                .in(RbacRole::getId, idList)
                .list()
                .stream().map(RsRbacRoleUtil::rbacRole2VO).collect(Collectors.toList());
    }

    public List<RbacRoleVO> getByIdList(List<String> idList, String appId) {
        return rbacRoleService.lambdaQuery()
                .in(RbacRole::getId, idList)
                .eq(RbacRole::getAppId, appId)
                .list()
                .stream().map(RsRbacRoleUtil::rbacRole2VO).collect(Collectors.toList());
    }

}
