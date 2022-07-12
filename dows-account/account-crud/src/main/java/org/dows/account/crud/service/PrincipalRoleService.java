package org.dows.account.crud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dows.account.crud.entity.PrincipalRole;
import org.dows.account.pojo.query.AccountRoleQuery;

import java.util.List;

/**
 * 账号-主体角色维度信息(PrincipalRole)表服务接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:45:26
 */
public interface PrincipalRoleService extends IService<PrincipalRole> {
    List<RoleVo> queryRoleById(List<Long> ids, Integer type);

    int updatePrincipalRole(List<PrincipalRole> list);

    int deleteRoleRel(List<Long> accountIds, int principalTyp, int isPlatform);

    List<RoleVo> accountRoleList(AccountRoleQuery reqAccountRole);
}
