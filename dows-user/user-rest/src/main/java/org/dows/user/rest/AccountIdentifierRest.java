package org.dows.user.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.entity.AccountIdentifier;
import org.dows.user.form.AccountIdentifierForm;
import org.dows.user.service.AccountIdentifierService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号标识(AccountIdentifier)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:26
 */
@Api(tags = "账号标识")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountIdentifier")
public class AccountIdentifierRest implements MybatisCrudRest<AccountIdentifierForm, AccountIdentifier, AccountIdentifierService> {

    //private final AccountIdentifierBiz accountIdentifierBiz;

}

