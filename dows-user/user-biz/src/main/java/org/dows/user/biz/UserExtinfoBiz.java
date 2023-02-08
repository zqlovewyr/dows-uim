package org.dows.user.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserExtinfoApi;
import org.dows.user.api.dto.UserExtinfoDTO;
import org.dows.user.entity.UserExtinfo;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserExtinfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2023/2/8 13:31
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserExtinfoBiz implements UserExtinfoApi {
    private final UserExtinfoService userExtinfoService;
    @Override
    public Response<String> insertUserExtinfo(UserExtinfoDTO userExtinfoDTO) {
        UserExtinfo userExtinfo = new UserExtinfo();
        BeanUtils.copyProperties(userExtinfoDTO, userExtinfo);
        boolean userFlag = userExtinfoService.save(userExtinfo);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_EXTINFO_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userExtinfo.getId().toString());
    }
}
