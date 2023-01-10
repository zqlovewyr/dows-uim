package org.dows.user.rest.admin;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.service.UserAddressService;
import org.dows.user.entity.UserAddress;
import org.dows.user.form.UserAddressForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户-地址维度(UserAddress)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:11
 */
@Api(tags = "用户-地址维度")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("userAddress")
public class UserAddressRest implements MybatisCrudRest<UserAddressForm, UserAddress, UserAddressService> {

    //private final UserAddressBiz userAddressBiz;

}

