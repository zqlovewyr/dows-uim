package org.dows.account.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dows.rbac.vo.GroupVo;
import org.dows.tenant.vo.TenantVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dows.account.api.AccountInstanceApi;
import org.dows.account.pojo.IdsUpdate;
import org.dows.account.pojo.form.*;
import org.dows.account.pojo.query.AccountOwnerQuery;
import org.dows.account.pojo.query.AccountRoleQuery;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.framework.api.Response;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 账号-实例维度信息(AccountInstance)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:45
 */
@Api(tags = "账号用户管理")
@RequestMapping("account")
public interface AccountInstanceRest<E, S extends IService> extends AccountInstanceApi {

    @ApiOperation(value = "查询用户列表")
    @GetMapping("/queryUserList")
    Response<IPage<AccountVo>> queryUserList(@RequestHeader("account-info") String accountInfo, UserListQuery userListQuery);

    @ApiOperation(value = "更新用户")
    @PostMapping("/updateUser")
    Response<String> updateUser(@RequestBody @Validated UserUpdate userUpdate);

    @ApiOperation(value = "批量删除用户")
    @DeleteMapping("/delUser")
    Response<String> delUser(@RequestBody IdsUpdate ids);

    @ApiOperation(value = "批量更新用户状态")
    @PutMapping("/batchUpdateStatus")
    Response<String> batchUpdateStatus(@RequestBody BatchUpdateStatusForm reqBatchUpdateStatus);

    @ApiOperation(value = "重置密码")
    @PutMapping("/restPassword")
    Response<String> restPassword(@RequestBody List<ReqResetPwd> reqResetPwds);


    @ApiOperation(value = "修改密码")
    @PostMapping("/changePassword")
    Response<String> changePassword(@RequestBody @Validated ChangePasswordForm reqChangePassword);

    @ApiOperation(value = "平台角色授权")
    @PutMapping("/platRoleSet")
    Response<String> platRoleSet(@RequestBody UserRoleRelReq userRoleRelReq);


    @ApiOperation(value = "账户下角色列表")
    @GetMapping("/accountRoleList")
    Response<List<Long>> accountRoleList(AccountRoleQuery reqAccountRole);

    @ApiOperation(value = "导入用户")
    @PostMapping("/importUser")
    Response<String> importExcel(@ApiParam(value = "导入文件", required = true)
                                 @RequestParam("file") MultipartFile file);

    @ApiOperation(value = "导出用户")
    @GetMapping("/exportUser")
    ResponseEntity<FileSystemResource> exportExcel(UserListQuery userListQuery);


    @ApiOperation(value = "账户所属租户")
    @GetMapping("/accountTenantList")
    Response<IPage<TenantVo>> accountTenantList(AccountOwnerQuery accountOwnerQuery);

    @ApiOperation(value = "账户所属用户组")
    @GetMapping("/accountGroupList")
    Response<IPage<GroupVo>> accountGroupList(AccountOwnerQuery accountOwnerQuery);


}
