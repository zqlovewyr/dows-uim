package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserExtinfoDTO;
import org.dows.user.api.dto.UserJobDTO;

/**
 * 用户-工作信息(UserJob)Api接口
 *
 * @author Administrator
 * @date 2023/2/8 13:57
 */
public interface UserJobApi {
    /**
     * 添加 用户-工作信息
     *
     * @param userJobDTO
     */
    Response<String> insertUserJob(UserJobDTO userJobDTO);
}
