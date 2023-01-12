package org.dows.account.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.api.Response;
import org.dows.rbac.api.dto.RbacRoleDto;
import org.dows.rbac.biz.RbacRoleBiz;
import org.dows.rbac.form.RbacRoleForm;
import org.dows.rbac.query.RbacRoleQuery;
import org.dows.rbac.vo.RbacRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author runsix
 */
@Api(tags = "权限-角色")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacRole")
public class AdminRbacRoleRest {

    private final RbacRoleBiz rbacRoleBiz;

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页获取角色列表")
    public Response<IPage<RbacRoleVo>> pageList(RbacRoleQuery rbacRoleQuery){
        return rbacRoleBiz.getRbacRoleListPage(rbacRoleQuery);
    }

    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation("保存及修改，（有id为修改）")
    public Response saveOrUpdate(@RequestBody RbacRoleForm rbacRoleForm){
        try {
            RbacRoleDto rbacRoleDto = new RbacRoleDto();
            BeanUtils.copyProperties(rbacRoleForm,rbacRoleDto);
            rbacRoleBiz.createOrUpdate(rbacRoleDto);
            return Response.ok();
        } catch (Exception e) {
            return Response.fail(e.toString());
        }
    }

    @GetMapping("{id}/info")
    @ApiOperation(value = "根据数据id获取详情")
    public Response<AccountOrgVo> getInfo(
            @ApiParam(required = true, value = "id") @PathVariable("id") Long id){
        return rbacRoleBiz.getRbacRoleById(id);
    }
    @DeleteMapping(
            path = {"/{id}"}
    )
    @ApiOperation(value = "删除组织架构数据")
    public Response<AccountOrgVo> deleteById(
            @ApiParam(required = true, value = "id") @PathVariable("id") Long id){
        return rbacRoleBiz.deleteById(id);
    }
}
