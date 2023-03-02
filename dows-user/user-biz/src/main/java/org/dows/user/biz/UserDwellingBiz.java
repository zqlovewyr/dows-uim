package org.dows.user.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserDwellingApi;
import org.dows.user.api.dto.UserDwellingDTO;
import org.dows.user.api.vo.UserDwellingVo;
import org.dows.user.api.vo.UserFamilyVo;
import org.dows.user.entity.UserDwelling;
import org.dows.user.entity.UserEducation;
import org.dows.user.entity.UserFamily;
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
    @DS("uim")
    public Response<String> insertUserDwelling(UserDwellingDTO userDwellingnDTO) {
        UserDwelling userDwelling = new UserDwelling();
        BeanUtils.copyProperties(userDwellingnDTO, userDwelling);
        boolean userFlag = userDwellingService.save(userDwelling);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_DWELLING_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userDwelling.getId().toString());
    }

    @Override
    @DS("uim")
    public Response<UserDwellingVo> getUserDwellingByPrincipalId(String principalId) {
        LambdaQueryWrapper<UserDwelling> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDwelling::getPrincipalId, principalId);
        UserDwelling userDwelling = userDwellingService.getOne(queryWrapper);
        //复制属性
        UserDwellingVo vo = new UserDwellingVo();
        if (userDwelling != null) {
            BeanUtils.copyProperties(userDwelling, vo);
            vo.setId(userDwelling.getId().toString());
        }
        return Response.ok(vo);
    }

    @Override
    @DS("uim")
    public Response<String> updateUserDwellingById(UserDwellingDTO userDwellingDTO) {
        UserDwelling userDwelling = new UserDwelling();
        BeanUtils.copyProperties(userDwellingDTO, userDwelling);
        userDwelling.setId(Long.valueOf(userDwellingDTO.getId()));
        boolean userFlag = userDwellingService.updateById(userDwelling);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_DWELLING_UPDATE_FAIL_EXCEPTION);
        }
        return Response.ok(userDwelling.getId().toString());
    }

    @Override
    @DS("uim")
    public Response<Boolean> deleteUserDwellingById(String id) {
        //1、获取对应数据
        UserDwelling userDwelling = userDwellingService.getById(id);
        if (userDwelling == null) {
            throw new UserException(EnumUserStatusCode.USER_FAMILY_IS_NOT_EXIST_EXCEPTION);
        }
        //1、删除用户家庭关系
        LambdaUpdateWrapper<UserDwelling> dwellingWrapper = Wrappers.lambdaUpdate(UserDwelling.class);
        dwellingWrapper.set(UserDwelling::getDeleted, true)
                .eq(UserDwelling::getId, id);
        return Response.ok(userDwellingService.update(dwellingWrapper));
    }
}
