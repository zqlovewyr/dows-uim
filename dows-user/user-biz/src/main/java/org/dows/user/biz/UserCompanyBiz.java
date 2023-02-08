package org.dows.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserCompanyApi;
import org.dows.user.api.dto.UserCompanyDTO;
import org.dows.user.api.dto.UserExtinfoDTO;
import org.dows.user.api.vo.UserCompanyVo;
import org.dows.user.api.vo.UserJobVo;
import org.dows.user.entity.UserCompany;
import org.dows.user.entity.UserExtinfo;
import org.dows.user.entity.UserJob;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserCompanyService;
import org.dows.user.service.UserExtinfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2023/2/8 13:40
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserCompanyBiz implements UserCompanyApi {
    private final UserCompanyService userCompanyService;
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
}
