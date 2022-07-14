package org.dows.rbac.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.framework.api.Response;
import org.dows.rbac.api.RbacRoleApi;
import org.dows.rbac.pojo.query.RoleAccountQuery;
import org.dows.rbac.pojo.query.RolePermQuery;
import org.dows.rbac.pojo.vo.GroupVo;
import org.dows.rbac.pojo.vo.RbacAccreditVo;
import org.dows.rbac.pojo.vo.RoleVo;
import org.dows.rbac.pojo.vo.SimpleRoleVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * RBAC-角色(RbacRole)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:46
 */
@Api(tags = "角色管理")
@RequestMapping("role")
public interface RbacRoleRest<E, S extends IService> extends RbacRoleApi {

    @GetMapping("/queryRoleList")
    @ApiOperation(value = "查询角色列表")
    Response<IPage<RoleVo>> queryRoleList(RoleListQuery roleListQuery);

    @GetMapping("/querySimpleRoleList")
    @ApiOperation(value = "查询角色列表")
    Response<List<SimpleRoleVo>> querySimpleRoleList(RoleListQueryO roleListQuery);

    @PostMapping("/update")
    @ApiOperation(value = "更新或者新增")
    Response<String> update(@RequestBody RoleUpdate roleUpdate);


    @DeleteMapping("/del/role")
    @ApiOperation(value = "删除角色")
    Response<String> deleteRole(@RequestBody IdsUpdate ids);

    @GetMapping("/queryRoleAccount")
    @ApiOperation(value = "查询角色下用户")
    Response<IPage<AccountVo>> queryRoleAccount(RoleAccountQuery roleAccountQuery);


    @GetMapping("/queryRoleGroup")
    @ApiOperation(value = "查询角色下用户组")
    Response<IPage<GroupVo>> queryRoleGroup(RoleAccountQuery roleAccountQuery);


    @PutMapping("/permForRole")
    @ApiOperation(value = "给角色赋权")
    Response<String> permForRole(@RequestBody List<PermRoleUpdate> permRoleList);

    @GetMapping("/queryRoleperm")
    @ApiOperation(value = "查询角色拥有的权限")
    Response<List<RbacAccreditVo>> queryRoleperm(RolePermQuery rolepermQuery);

    @PutMapping("/roleCopy")
    @ApiOperation(value = "角色复制")
    Response<String> copyRole(@RequestBody RoleCopy roleCopy);
}
