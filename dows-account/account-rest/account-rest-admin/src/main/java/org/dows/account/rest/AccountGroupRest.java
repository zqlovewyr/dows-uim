package org.dows.account.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountGroupApi;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.entity.AccountGroup;
import org.dows.account.form.AccountGroupForm;
import org.dows.account.service.AccountGroupService;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 账号-组(AccountGroup)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:25
 */
@Api(tags = "账号-组")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountGroup")
public class AccountGroupRest implements MybatisCrudRest<AccountGroupForm, AccountGroup, AccountGroupService> {

    private final AccountGroupApi accountGroupApi;

    @ApiOperation("保存 账号-组织")
    @PostMapping("/createAccountGroups")
    public Response batchCreateAccountGroup(@RequestBody List<AccountOrgGroupDTO> accountOrgGroupDTOS) {
        accountGroupApi.batchInsertGroup(accountOrgGroupDTOS);
        return Response.ok();
    }
}

