package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.PrincipalRole;
import org.dows.account.crud.mapper.PrincipalRoleMapper;
import org.dows.account.crud.service.PrincipalRoleService;
import org.dows.account.pojo.query.AccountRoleQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号-主体角色维度信息(PrincipalRole)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:45:26
 */
@Service
public class PrincipalRoleServiceImpl extends ServiceImpl<PrincipalRoleMapper, PrincipalRole> implements PrincipalRoleService {
    @Override
    public List<RoleVo> queryRoleById(List<Long> ids, Integer type) {
        return this.baseMapper.queryRoleById(ids, type);
    }

    @Override
    public int updatePrincipalRole(List<PrincipalRole> list) {
        //查询相关数
        list.forEach(item -> {
            baseMapper.updatePrincipalRole(item);
        });
        return 1;
    }

    @Override
    public int deleteRoleRel(List<Long> accountIds, int principalTyp, int isPlatform) {
        return this.baseMapper.deleteRoleRel(accountIds, principalTyp, isPlatform);
    }

    @Override
    public List<RoleVo> accountRoleList(AccountRoleQuery reqAccountRole) {
        return this.baseMapper.accountRoleList(reqAccountRole);
    }
}
