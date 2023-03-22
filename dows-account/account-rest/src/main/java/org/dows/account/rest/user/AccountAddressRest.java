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

}

