package org.dows.account.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dows.account.form.*;
import org.dows.rbac.vo.GroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.dows.account.api.AccountOrganizationApi;
import org.dows.account.pojo.IdsUpdate;
import org.dows.account.pojo.form.BatchUpdateStatusForm;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 账号-组织架构维度信息(AccountOrganization)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:45
 */
@Api(tags = "用户组/组织管理")
@RequestMapping("group")
public interface AccountOrganizationRest<E, S extends IService> extends AccountOrganizationApi {

    @ApiOperation(value = "查询用户组列表")
    @GetMapping("/queryGroupList")
    Response<IPage<GroupVo>> queryGroupList(GroupListQuery groupListQuery);

    @ApiOperation(value = "更新用户组")
    @PostMapping("/updateGroup")
    Response<String> updateGroup(@RequestBody GroupUpdate groupUpdate);

    @ApiOperation(value = "批量删除用户组")
    @DeleteMapping("/delAccountGroup")
    Response<String> delAccountGroup(@RequestBody IdsUpdate ids);


    @ApiOperation(value = "批量更新用户组状态")
    @PutMapping("/batchUpdateStatus")
    Response<String> batchUpdateStatus(@RequestBody BatchUpdateStatusForm reqBatchUpdateStatus);

    @ApiOperation(value = "查询用户组下用户列表")
    @GetMapping("/queryAccountGroup")
    Response<IPage<AccountVo>> queryAccountGroup(GroupUserQuery groupUserQuery);

    @ApiOperation(value = "用户组授权角色")
    @PutMapping("/platRoleSet")
    Response<String> platRoleSet(@RequestBody GroupRoleRelReq groupRoleRelReq);

    @ApiOperation(value = "用户组下的角色")
    @GetMapping("/groupRoleList")
    Response<List<Long>> groupRoleList(Long orgId);

    @ApiOperation(value = "用户组下加人")
    @PutMapping("/groupAddAccount")
    Response<String> groupAddAccount(@RequestBody GroupAddAccountReq groupAddAccountReq);
}
