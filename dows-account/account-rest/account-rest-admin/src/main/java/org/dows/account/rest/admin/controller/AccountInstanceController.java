package org.dows.account.rest.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountInstanceBiz;
import org.dows.account.crud.entity.AccountInstance;
import org.dows.account.crud.service.AccountInstanceService;
import org.dows.account.pojo.form.*;
import org.dows.account.pojo.query.AccountOwnerQuery;
import org.dows.account.pojo.query.AccountRoleQuery;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.account.rest.admin.AccountInstanceRest;
import org.dows.auth.api.dto.AuthUserDto;
import org.dows.framework.api.Response;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 账号管理
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:53:32
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountInstanceController implements AccountInstanceRest<AccountInstance, AccountInstanceService> {
    private final AccountInstanceBiz accountInstanceBiz;

    @Override
    public Response<IPage<AccountVo>> queryUserList(@RequestHeader("account-info") String accountInfo, UserListQuery userListQuery) {
        return Response.ok(accountInstanceBiz.queryUserList(userListQuery));
    }


    @Override
    public Response<String> updateUser(UserUpdate userUpdate) {
        return Response.ok(accountInstanceBiz.updateUser(userUpdate));
    }

    @Override
    public Response<String> delUser(IdsUpdate ids) {
        return Response.ok(accountInstanceBiz.delUser(ids.getIds()));
    }

    @Override
    public Response<String> batchUpdateStatus(BatchUpdateStatusForm batchUpdateStatus) {
        return Response.ok(accountInstanceBiz.batchUpdateStatus(batchUpdateStatus));
    }

    @Override
    public Response<String> restPassword(List<ReqResetPwd> reqResetPwds) {
        return Response.ok(accountInstanceBiz.restPassword(reqResetPwds));
    }

    @Override
    public Response<String> changePassword(ChangePasswordForm reqChangePassword) {
        return Response.ok(accountInstanceBiz.changePassword(reqChangePassword));
    }

    @Override
    public Response<String> platRoleSet(UserRoleRelReq userRoleRelReq) {
        return Response.ok(accountInstanceBiz.platRoleSet(userRoleRelReq));
    }

    @Override
    public Response<List<Long>> accountRoleList(AccountRoleQuery reqAccountRole) {
        return Response.ok(accountInstanceBiz.accountRoleList(reqAccountRole));
    }


    @Override
    public Response<String> importExcel(MultipartFile file) {
        //
        accountInstanceBiz.importExcel(file);
        return Response.ok("success");
    }

    @Override
    public ResponseEntity<FileSystemResource> exportExcel(UserListQuery userListQuery) {

        return export(accountInstanceBiz.exportData(userListQuery));

    }

    @Override
    public Response<IPage<TenantVo>> accountTenantList(AccountOwnerQuery accountOwnerQuery) {
        return Response.ok(accountInstanceBiz.accountOwnerQuery(accountOwnerQuery));
    }

    @Override
    public Response<IPage<GroupVo>> accountGroupList(AccountOwnerQuery accountOwnerQuery) {
        return Response.ok(accountInstanceBiz.accountGroupList(accountOwnerQuery));
    }


    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }

    @Override
    public Response<Boolean> createAccount(AuthUserDto authUs) {
        return null;
    }
}
