package org.dows.user.rest.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.vo.AccountGroupInfoVo;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.api.api.UserFamilyApi;
import org.dows.user.api.vo.UserFamilyVo;
import org.dows.user.entity.UserFamily;
import org.dows.user.form.UserFamilyForm;
import org.dows.user.service.UserFamilyService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @date 2023/2/2 15:09
 */
@Api(tags = "用户-家庭")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("userFamily")
public class UserFamilyRest implements MybatisCrudRest<UserFamilyForm, UserFamily, UserFamilyService> {
    private final UserFamilyApi userFamilyApi;

    @ApiOperation("用户-家庭")
    @GetMapping("/getGenealogyList/{id}")
    public Response<UserFamilyVo> getGenealogyList(@PathVariable("id") Long id) {
        Response<UserFamilyVo> vo = userFamilyApi.getGenealogyList(id.toString());
        return vo;
    }
}
