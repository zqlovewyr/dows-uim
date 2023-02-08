package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserEducationDTO;
import org.dows.user.api.dto.UserExtinfoDTO;
import org.dows.user.api.vo.UserEducationVo;

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

    /**
     * 查看 用户-教育信息
     *
     * @param userId
     */
    Response<UserEducationVo> getUserEducationByUserId(String userId);


    /**
     * 编辑 用户-教育信息
     *
     * @param userEducationDTO
     */
    Response<String> updateUserEducationById(UserEducationDTO userEducationDTO);

    /**
     * 删除单个 用户-教育信息
     *
     * @param id
     */
    Response<Boolean> deleteUserEducationById(String id);
}
