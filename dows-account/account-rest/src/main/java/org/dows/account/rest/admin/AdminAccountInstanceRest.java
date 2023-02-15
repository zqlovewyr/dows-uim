package org.dows.account.rest.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountInstanceBiz;
import org.dows.account.biz.dto.AccountInstanceResDTO;
import org.dows.account.form.AccountUserResForm;
import org.dows.account.query.AccountInstanceQuery;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.framework.api.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 用户(Users)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-23 22:05:09
 */
@Api(tags = "用户基础信息")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("admin/account")
public class AdminAccountInstanceRest {

    private final AccountInstanceBiz accountInstanceBiz;


    @GetMapping(value = "/page")
    @ApiOperation(value = "分页获取用户基础信息")
    public Response<IPage<AccountInstanceResVo>> pageList(AccountInstanceQuery accountInstanceQuery){
        return Response.ok(accountInstanceBiz.getAccountInstanceListPage(accountInstanceQuery));
    }

    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation("保存及修改用户，（有id为修改）")
    public Response saveOrUpdate(@RequestBody AccountUserResForm usersForm){
        try {
            AccountInstanceResDTO accountInstanceResDTO = new AccountInstanceResDTO();
            BeanUtils.copyProperties(usersForm,accountInstanceResDTO);
            accountInstanceBiz.saveOrUpdateAccountInstance(accountInstanceResDTO);
            return Response.ok();

        } catch (Exception e) {
            return Response.fail(e.toString());
        }
    }

    @GetMapping("{id}/info")
    @ApiOperation(value = "根据数据id获取详情")
    public Response<AccountInstanceResVo> getInfo(
            @ApiParam(required = true, value = "id") @PathVariable("id") Long id){
        return Response.ok(accountInstanceBiz.getAccountInstanceInfo(id));
    }
    @DeleteMapping(
            path = {"/{id}"}
    )
    @ApiOperation(value = "删除用户数据")
    public Response<Boolean> deleteById(
            @ApiParam(required = true, value = "id") @PathVariable("id") Long id){
        return Response.ok(accountInstanceBiz.deleteById(id));
    }
}

