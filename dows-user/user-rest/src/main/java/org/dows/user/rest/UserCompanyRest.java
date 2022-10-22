package org.dows.user.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.entity.UserCompany;
import org.dows.user.form.UserCompanyForm;
import org.dows.user.service.UserCompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户-公司(UserCompany)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:12
 */
@Api(tags = "用户-公司")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("userCompany")
public class UserCompanyRest implements MybatisCrudRest<UserCompanyForm, UserCompany, UserCompanyService> {

    //private final UserCompanyBiz userCompanyBiz;

}

