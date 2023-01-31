package org.dows.account.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountRoleApi;
import org.dows.account.dto.AccountRoleDTO;
import org.dows.account.entity.AccountRole;
import org.dows.account.form.AccountRoleForm;
import org.dows.account.service.AccountRoleService;
import org.dows.account.vo.AccountRoleVo;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.*;

/**
 * 账号-角色(AccountRole)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:29
 */
@Api(tags = "账号-角色")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountRole")
public class AccountRoleRest implements MybatisCrudRest<AccountRoleForm, AccountRole, AccountRoleService> {
    private final AccountRoleApi accountRoleApi;

    @ApiOperation("自定义查询 账号-角色 列表")
    @PostMapping("/customAccountRoleList")
    public Response customAccountRoleList(@RequestBody AccountRoleDTO accountRoleDTO) {
        Response<IPage<AccountRoleVo>> pageList = accountRoleApi.customAccountRoleList(accountRoleDTO);
        return Response.ok(pageList);
    }


    @ApiOperation("查看 角色-信息")
    @GetMapping("/getAccountRoleById/{id}")
    public Response getAccountRoleById(@PathVariable("id") long id) {
        Response<AccountRoleVo> vo = accountRoleApi.getAccountRoleById(id);
        return Response.ok(vo);
    }

}

