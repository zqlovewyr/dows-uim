package org.dows.user.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserEducationApi;
import org.dows.user.api.dto.UserEducationDTO;
import org.dows.user.api.vo.UserEducationVo;
import org.dows.user.entity.UserCompany;
import org.dows.user.entity.UserEducation;
import org.dows.user.entity.UserJob;
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
    @DS("uim")
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
    @DS("uim")
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

    @Override
    @DS("uim")
    public Response<String> updateUserEducationById(UserEducationDTO userEducationDTO) {
        UserEducation userEducation = new UserEducation();
        BeanUtils.copyProperties(userEducationDTO, userEducation);
        userEducation.setId(Long.valueOf(userEducationDTO.getId()));
        boolean userFlag = userEducationService.updateById(userEducation);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_EDUCATION_UPDATE_FAIL_EXCEPTION);
        }
        return Response.ok(userEducation.getId().toString());
    }

    @Override
    @DS("uim")
    public Response<Boolean> deleteUserEducationById(String id) {
        //1、获取对应数据
        UserEducation userEducation = userEducationService.getById(id);
        if (userEducation == null) {
            throw new UserException(EnumUserStatusCode.USER_EDUCATION_IS_NOT_EXIST_EXCEPTION);
        }
        //1、删除用户教育关系
        LambdaUpdateWrapper<UserEducation> educationWrapper = Wrappers.lambdaUpdate(UserEducation.class);
        educationWrapper.set(UserEducation::getDeleted, true)
                .eq(UserEducation::getId, id);
        return Response.ok(userEducationService.update(educationWrapper));
    }
}
