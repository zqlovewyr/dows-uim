package org.dows.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserCompanyApi;
import org.dows.user.api.dto.UserCompanyDTO;
import org.dows.user.api.vo.UserCompanyVo;
import org.dows.user.entity.UserCompany;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserCompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserCompanyBiz implements UserCompanyApi {


    private final UserCompanyService userCompanyService;
    public UserCompany getOneUserCompany(UserCompanyRequest userCompanyRequest) {

        return userCompanyService.lambdaQuery()
                .select(UserCompany::getCertNo,UserCompany::getCompanyCode,UserCompany::getId)
                .eq(UserCompany::getCertNo,userCompanyRequest.getCertNo())
                .one();
    }

    public void saveUserCompany(UserCompanyRequest userCompanyRequest) {
        UserCompany userCompany = new UserCompany();
        BeanUtils.copyProperties(userCompanyRequest,userCompany);
        userCompanyService.save(userCompany);
    }
    @Override
    public Response<String> insertUserCompany(UserCompanyDTO userCompanyDTO) {
        UserCompany userCompany = new UserCompany();
        BeanUtils.copyProperties(userCompanyDTO, userCompany);
        boolean userFlag = userCompanyService.save(userCompany);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_COMPANY_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userCompany.getId().toString());
    }

    @Override
    public Response<UserCompanyVo> getUserCompanyByUserId(String userId) {
        LambdaQueryWrapper<UserCompany> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserCompany::getUserId, userId);
        UserCompany userCompany = userCompanyService.getOne(queryWrapper);
        //复制属性
        UserCompanyVo vo = new UserCompanyVo();
        if (userCompany != null) {
            BeanUtils.copyProperties(userCompany, vo);
            vo.setId(userCompany.getId().toString());
        }
        return Response.ok(vo);
    }

    @Override
    public Response<UserCompanyVo> getUserCompany(UserCompanyDTO userCompanyDTO) {
        UserCompany userCompany = userCompanyService.lambdaQuery()
                .select(UserCompany::getCertNo, UserCompany::getCompanyCode, UserCompany::getId)
                .eq(UserCompany::getCertNo, userCompanyDTO.getCertNo())
                .one();
        UserCompanyVo vo = new UserCompanyVo();
        if (userCompany != null) {
            BeanUtils.copyProperties(userCompany, vo);
            vo.setId(userCompany.getId().toString());
        }
        return Response.ok(vo);
    }

    @Override
    public Response<String> updateUserCompanyById(UserCompanyDTO userCompanyDTO) {
        UserCompany userCompany = new UserCompany();
        BeanUtils.copyProperties(userCompanyDTO, userCompany);
        userCompany.setId(Long.valueOf(userCompanyDTO.getId()));
        boolean userFlag = userCompanyService.updateById(userCompany);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_COMPANY_UPDATE_FAIL_EXCEPTION);
        }
        return Response.ok(userCompany.getId().toString());
    }

    @Override
    public Response<Boolean> deleteUserCompanyById(String id) {
        //1、获取对应数据
        UserCompany userCompany = userCompanyService.getById(id);
        if (userCompany == null) {
            throw new UserException(EnumUserStatusCode.USER_JOB_IS_NOT_EXIST_EXCEPTION);
        }
        //1、删除用户公司关系
        LambdaUpdateWrapper<UserCompany> companyWrapper = Wrappers.lambdaUpdate(UserCompany.class);
        companyWrapper.set(UserCompany::getDeleted, true)
                .eq(UserCompany::getId, id);
        return Response.ok(userCompanyService.update(companyWrapper));
    }
}
