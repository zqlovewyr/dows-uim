package org.dows.rbac.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dows.framework.api.Response;
import org.dows.rbac.api.RbacPermissionApi;
import org.dows.rbac.pojo.form.PermTypeQuery;
import org.dows.rbac.pojo.form.PermUpdate;
import org.dows.rbac.query.PermCateQuery;
import org.dows.rbac.query.PermListQuery;
import org.dows.rbac.vo.PermSimpleVo;
import org.dows.rbac.vo.PermVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * RBAC权限资源(RbacPermission)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:46
 */
@Api(tags = "权限管理")
@RequestMapping("permission")
public interface RbacPermissionRest<E, S extends IService> extends RbacPermissionApi {


    @GetMapping("/queryPermList")
    @ApiOperation(value = "查询权限列表")
    Response<IPage<PermVo>> queryPermList(PermListQuery permListQuery);

    @GetMapping("/queryAllPermList")
    @ApiOperation(value = "根据产品类型查询权限列表")
    Response<List<PermSimpleVo>> queryAllPermList(PermCateQuery permCateQuery);

    @GetMapping("/queryPermSimpleList")
    @ApiOperation(value = "查询某个类型列表")
    Response<List<PermSimpleVo>> queryPermSimpleList(PermTypeQuery permTypeQuery);

    @ApiOperation(value = "更新或者新增")
    @PostMapping("/update")
    Response<String> update(@RequestBody PermUpdate permUpdate);


    @ApiOperation(value = "删除权限")
    @DeleteMapping("/del/perm")
    Response<String> deletePerm(@RequestBody IdsUpdate ids);

    @ApiOperation(value = "获取当前登录人权限列表")
    @GetMapping("/queryCurrentPermList")
    Response<List<PermDTO>> queryCurrentPermList(@RequestHeader("account-info") String accountInfo);

    @ApiOperation(value = "getHeader")
    @GetMapping("/getHeader")
    Response<String> getHeader(@RequestHeader("account-info") String accountInfo);

}
