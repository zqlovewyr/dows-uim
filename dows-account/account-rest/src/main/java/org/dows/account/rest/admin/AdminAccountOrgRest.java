package org.dows.account.rest.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountOrgBiz;
import org.dows.account.biz.dto.AccountOrgDTO;
import org.dows.account.form.AccountOrgForm;
import org.dows.account.query.AccountOrgQuery;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.api.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 账号-组织架构表控制层
 *
 * @author lait.zhang
 * @since 2022-12-23 22:05:09
 */
@Api(tags = "组织架构")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("admin/accountOrg")
public class AdminAccountOrgRest {

    private final AccountOrgBiz accountOrgBiz;


    @GetMapping(value = "/page")
    @ApiOperation(value = "分页获取组织架构")
    public Response<IPage<AccountInstanceResVo>> pageList(AccountOrgQuery accountOrgQuery){
        return accountOrgBiz.getAccountOrgListPage(accountOrgQuery);
    }

    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation("保存及修改，（有id为修改）")
    public Response saveOrUpdate(@RequestBody AccountOrgForm accountOrgForm){
        try {
            AccountOrgDTO accountOrgDTO = new AccountOrgDTO();
            BeanUtils.copyProperties(accountOrgForm,accountOrgDTO);
            accountOrgBiz.createAccountOrg(accountOrgDTO);
            return Response.ok();
        } catch (Exception e) {
            return Response.fail(e.toString());
        }
    }

    @GetMapping("{id}/info")
    @ApiOperation(value = "根据数据id获取详情")
    public Response<AccountOrgVo> getInfo(
            @ApiParam(required = true, value = "id") @PathVariable("id") Long id){
        return accountOrgBiz.getAccountOrgById(id);
    }
    @DeleteMapping(
            path = {"/{id}"}
    )
    @ApiOperation(value = "删除组织架构数据")
    public Response<AccountOrgVo> deleteById(
            @ApiParam(required = true, value = "id") @PathVariable("id") Long id){
        return accountOrgBiz.deleteById(id);
    }
}

