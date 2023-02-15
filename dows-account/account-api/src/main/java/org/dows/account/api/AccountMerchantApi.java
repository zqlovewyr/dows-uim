package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dows.account.form.AccountUserResForm;
import org.dows.account.query.AccountInstanceQuery;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号-实例维度信息(AccountInstance)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:25
 */
@Api(tags = "账号")
@RequestMapping("accountMerchant")
public interface AccountMerchantApi {

    @PostMapping(value = "/saveAccountUser")
    @ApiOperation("保存用户")
    Response<AccountInstanceVo> saveAccountUser(@RequestBody AccountUserResForm usersForm);

}
