package org.dows.account.rest.user;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.entity.AccountAddress;
import org.dows.account.form.AccountAddressForm;
import org.dows.account.service.AccountAddressService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号-用户地址控制层
 *
 * @author lait.zhang
 * @since 2022-12-23 22:05:09
 */
@Api(tags = "用户地址")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user/account/address")
public class AccountAddressRest implements MybatisCrudRest<AccountAddressForm, AccountAddress, AccountAddressService> {

    // private final AccountAddressBiz accountAddressBiz;


//    @GetMapping(value = "/page")
//    @ApiOperation(value = "分页获取职位列表")
//    public Response<IPage<AccountPostVo>> pageList(AccountPostQuery accountPostQuery){
//        return accountPostBiz.getAccountOrgListPage(accountPostQuery);
//    }
//
//    @PostMapping(value = "/saveOrUpdate")
//    @ApiOperation("保存及修改，（有id为修改）")
//    public Response saveOrUpdate(@RequestBody AccountPostForm accountPostForm){
//        try {
//            AccountPostDTO accountPostDTO = new AccountPostDTO();
//            BeanUtils.copyProperties(accountPostForm,accountPostDTO);
//            accountPostBiz.createAccountOrg(accountPostDTO);
//            return Response.ok();
//        } catch (Exception e) {
//            return Response.fail(e.toString());
//        }
//    }
//
//    @GetMapping("{id}/info")
//    @ApiOperation(value = "根据数据id获取详情")
//    public Response<AccountPostVo> getInfo(
//            @ApiParam(required = true, value = "id") @PathVariable("id") Long id){
//        return accountPostBiz.getAccountOrgById(id);
//    }
//    @DeleteMapping(
//            path = {"/{id}"}
//    )
//    @ApiOperation(value = "删除组织架构数据")
//    public Response<AccountPostVo> deleteById(
//            @ApiParam(required = true, value = "id") @PathVariable("id") Long id){
//        return accountPostBiz.deleteById(id);
//    }
}

