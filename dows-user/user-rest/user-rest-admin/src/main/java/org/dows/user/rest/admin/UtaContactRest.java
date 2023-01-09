package org.dows.user.rest.admin;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.entity.UtaContact;
import org.dows.user.form.UtaContactForm;
import org.dows.user.service.UtaContactService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 联系需求发布者(UtaContact)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:13
 */
@Api(tags = "联系需求发布者")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("utaContact")
public class UtaContactRest implements MybatisCrudRest<UtaContactForm, UtaContact, UtaContactService> {

    //private final UtaContactBiz utaContactBiz;

}

