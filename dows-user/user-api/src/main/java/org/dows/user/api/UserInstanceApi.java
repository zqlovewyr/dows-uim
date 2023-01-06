package org.dows.user.api;

import org.dows.user.api.request.RealNameAuthRequest;
import org.dows.user.api.response.RealNameAuthResponse;

public interface UserInstanceApi {


    RealNameAuthResponse realNameAuth(RealNameAuthRequest realNameAuthRequest);
}
