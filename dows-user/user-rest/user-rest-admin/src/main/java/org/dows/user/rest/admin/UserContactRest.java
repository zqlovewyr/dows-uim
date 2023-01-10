package org.dows.user.rest.admin;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.entity.UserContact;
import org.dows.user.service.UserContactService;
import org.dows.user.form.UserContactForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户-联系人(UserContact)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:12
 */
@Api(tags = "用户-联系人")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("userContact")
public class UserContactRest implements MybatisCrudRest<UserContactForm, UserContact, UserContactService> {

    //private final UserContactBiz userContactBiz;

}

