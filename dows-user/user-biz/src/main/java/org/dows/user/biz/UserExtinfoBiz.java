package org.dows.user.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserExtinfoApi;
import org.dows.user.api.dto.UserExtinfoDTO;
import org.dows.user.api.vo.UserExtinfoVo;
import org.dows.user.entity.UserExtinfo;
import org.dows.user.entity.UserFamily;
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
    @DS("uim")
    public Response<String> insertUserExtinfo(UserExtinfoDTO userExtinfoDTO) {
        UserExtinfo userExtinfo = new UserExtinfo();
        BeanUtils.copyProperties(userExtinfoDTO, userExtinfo);
        boolean userFlag = userExtinfoService.save(userExtinfo);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_EXTINFO_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userExtinfo.getId().toString());
    }

    @Override
    @DS("uim")
    public Response<UserExtinfoVo> getUserExtinfoByUserId(String userId) {
        LambdaQueryWrapper<UserExtinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserExtinfo::getUserId, userId);
        UserExtinfo userExtinfo = userExtinfoService.getOne(queryWrapper);
        //复制属性
        UserExtinfoVo vo = new UserExtinfoVo();
        if (userExtinfo != null) {
            BeanUtils.copyProperties(userExtinfo, vo);
            vo.setId(userExtinfo.getId().toString());
        }
        return Response.ok(vo);
    }

    @Override
    @DS("uim")
    public Response<String> updateUserExtinfoById(UserExtinfoDTO userExtinfoDTO) {
        UserExtinfo userExtinfo = new UserExtinfo();
        BeanUtils.copyProperties(userExtinfoDTO, userExtinfo);
        userExtinfo.setId(Long.valueOf(userExtinfoDTO.getId()));
        boolean userFlag = userExtinfoService.updateById(userExtinfo);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_EXTINFO_UPDATE_FAIL_EXCEPTION);
        }
        return Response.ok(userExtinfo.getId().toString());
    }

    @Override
    @DS("uim")
    public Response<Boolean> deleteUserExtinfoById(String id) {
        //1、获取对应数据
        UserExtinfo userExtinfo = userExtinfoService.getById(id);
        if (userExtinfo == null) {
            throw new UserException(EnumUserStatusCode.USER_EXTINFO_IS_NOT_EXIST_EXCEPTION);
        }
        //1、删除用户扩展关系
        LambdaUpdateWrapper<UserExtinfo> extinfoWrapper = Wrappers.lambdaUpdate(UserExtinfo.class);
        extinfoWrapper.set(UserExtinfo::getDeleted, true)
                .eq(UserExtinfo::getId, id);
        return Response.ok(userExtinfoService.update(extinfoWrapper));
    }
}
