package org.dows.account.biz;

import org.dows.framework.api.Response;
import org.dows.user.api.UserInstanceApi;
import org.dows.user.api.request.RealNameAuthRequest;
import org.dows.user.api.response.RealNameAuthResponse;

/**
 * 账号认证
 */
public class AccountAuthBiz {
    private UserInstanceApi userInstanceApi;


    /**
     * 实名认证
     * @return
     */
    public Response realNameAuth(RealNameForm  realNameForm){


        RealNameAuthRequest realNameAuthRequest = new RealNameAuthRequest();
        RealNameAuthResponse realNameAuthResponse = userInstanceApi.realNameAuth(realNameAuthRequest);

        return  Response.ok(realNameAuthResponse);

    }
}
