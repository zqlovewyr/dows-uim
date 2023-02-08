package org.dows.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserEducationApi;
import org.dows.user.api.dto.UserEducationDTO;
import org.dows.user.api.vo.UserEducationVo;
import org.dows.user.api.vo.UserFamilyVo;
import org.dows.user.entity.UserEducation;
import org.dows.user.entity.UserExtinfo;
import org.dows.user.entity.UserFamily;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserEducationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2023/2/8 13:51
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserEducationBiz implements UserEducationApi {
    private final UserEducationService userEducationService;

    @Override
    public Response<String> insertUserEducation(UserEducationDTO userEducationDTO) {
        UserEducation userEducation = new UserEducation();
        BeanUtils.copyProperties(userEducationDTO, userEducation);
        boolean userFlag = userEducationService.save(userEducation);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_EDUCATION_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userEducation.getId().toString());
    }

    @Override
    public Response<UserEducationVo> getUserEducationByUserId(String userId) {
        LambdaQueryWrapper<UserEducation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEducation::getUserId, userId);
        UserEducation userEducation = userEducationService.getOne(queryWrapper);
        //复制属性
        UserEducationVo vo = new UserEducationVo();
        if (userEducation != null) {
            BeanUtils.copyProperties(userEducation, vo);
            vo.setId(userEducation.getId().toString());
        }
        return Response.ok(vo);
    }
}
