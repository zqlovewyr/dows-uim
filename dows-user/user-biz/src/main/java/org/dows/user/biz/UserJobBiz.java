package org.dows.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserJobApi;
import org.dows.user.api.dto.UserExtinfoDTO;
import org.dows.user.api.dto.UserJobDTO;
import org.dows.user.api.vo.UserExtinfoVo;
import org.dows.user.api.vo.UserJobVo;
import org.dows.user.entity.UserExtinfo;
import org.dows.user.entity.UserJob;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserExtinfoService;
import org.dows.user.service.UserJobService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2023/2/8 14:00
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserJobBiz implements UserJobApi {
    private final UserJobService userJobService;
    @Override
    public Response<String> insertUserJob(UserJobDTO userJobDTO) {
        UserJob userJob = new UserJob();
        BeanUtils.copyProperties(userJobDTO, userJob);
        boolean userFlag = userJobService.save(userJob);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_EXTINFO_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userJob.getId().toString());
    }

    @Override
    public Response<UserJobVo> getUserJobByUserId(String userId) {
        LambdaQueryWrapper<UserJob> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserJob::getUserId, userId);
        UserJob userJob = userJobService.getOne(queryWrapper);
        //复制属性
        UserJobVo vo = new UserJobVo();
        if (userJob != null) {
            BeanUtils.copyProperties(userJob, vo);
            vo.setId(userJob.getId().toString());
        }
        return Response.ok(vo);
    }
}
