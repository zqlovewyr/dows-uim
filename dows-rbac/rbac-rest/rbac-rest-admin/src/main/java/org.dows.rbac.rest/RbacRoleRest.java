package org.dows.rbac.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.rbac.api.RbacRoleApi;
import org.dows.rbac.dto.RbacRoleDTO;
import org.dows.rbac.vo.RbacRoleVo;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.form.RbacRoleForm;
import org.dows.rbac.service.RbacRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author runsix
 */
@Api(tags = "权限-角色")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacRole")
public class RbacRoleRest implements MybatisCrudRest<RbacRoleForm, RbacRole, RbacRoleService> {
    private final RbacRoleApi rbacRoleApi;

    @GetMapping("/v1/rbac-role/{id}")
    public Response<RbacRoleVo> getById(@PathVariable String id) {
        return rbacRoleApi.getById(id);
    }


    @GetMapping("/v1/rbac-role")
    public Response<List<RbacRoleVo>> getByIdList(List<String> rbacRoleIdList) {
        return rbacRoleApi.getByIdList(rbacRoleIdList);
    }

    @GetMapping("/v1/rbac-roles/{appid}")
    public Response<List<RbacRoleVo>> getByIdListAndAppId(List<String> rbacRoleIdList, String appid) {
        return rbacRoleApi.getByIdList(rbacRoleIdList,appid);
    }

    /**
     * 自定义查询 角色-实例 列表
     *
     * @param rbacRoleDTO
     */
    @ApiOperation("自定义查询 角色-实例 列表")
    @PostMapping("/customRbacRoleList")
    public Response<IPage<RbacRoleVo>> customRbacRoleList(RbacRoleDTO rbacRoleDTO) {
        return rbacRoleApi.customRbacRoleList(rbacRoleDTO);
    }
}
