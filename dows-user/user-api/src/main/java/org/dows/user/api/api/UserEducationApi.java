package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserEducationDTO;
import org.dows.user.api.dto.UserExtinfoDTO;

/**
 * 用户-教育信息(UserEducation)Api接口
 *
 * @author Administrator
 * @date 2023/2/8 13:48
 */
public interface UserEducationApi {

    /**
     * 添加 用户-教育信息
     *
     * @param userEducationDTO
     */
    Response<String> insertUserEducation(UserEducationDTO userEducationDTO);
}
