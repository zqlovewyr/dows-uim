package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dows.account.crud.entity.PrincipalRole;
import org.dows.account.pojo.query.AccountRoleQuery;

import java.util.List;

/**
 * 账号-主体角色维度信息(PrincipalRole)
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:57:31
 */
@Mapper
public interface PrincipalRoleMapper extends BaseMapper<PrincipalRole> {
    @Select("@PrincipalRoleMapper.queryRoleById")
    List<RoleVo> queryRoleById(@Param("ids") List<Long> ids, @Param("type") Integer type);

    @Update("insert into principal_role(role_id,principal_id,principal_typ,dt,deleted) " +
            "values (#{roleId},#{principalId},#{principalTyp},now(),0) on DUPLICATE key update " +
            "dt = now(),deleted = 0")
    int updatePrincipalRole(PrincipalRole role);

    @Update("@PrincipalRoleMapper.deleteRoleRel")
    int deleteRoleRel(@Param("accountIds") List<Long> accountIds, @Param("principalTyp") int principalTyp, @Param("isPlatform") int isPlatform);

    @Select("@PrincipalRoleMapper.accountRoleList")
    List<RoleVo> accountRoleList(@Param("p") AccountRoleQuery reqAccountRole);
}
