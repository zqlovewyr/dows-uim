package org.dows.user.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserDwellingApi;
import org.dows.user.api.dto.UserDwellingDTO;
import org.dows.user.entity.UserDwelling;
import org.dows.user.entity.UserEducation;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserDwellingService;
import org.dows.user.service.UserEducationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2023/2/8 14:07
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserDwellingBiz implements UserDwellingApi {

    private final UserDwellingService userDwellingService;

    @Override
    public Response<String> insertUserDwelling(UserDwellingDTO userDwellingnDTO) {
        UserDwelling userDwelling = new UserDwelling();
        BeanUtils.copyProperties(userDwellingnDTO, userDwelling);
        boolean userFlag = userDwellingService.save(userDwelling);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_DWELLING_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userDwelling.getId().toString());
    }
}
