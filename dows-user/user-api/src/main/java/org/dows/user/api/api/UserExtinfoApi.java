package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserExtinfoDTO;
import org.dows.user.api.dto.UserFamilyDTO;
import org.dows.user.api.vo.UserExtinfoVo;
import org.dows.user.api.vo.UserFamilyVo;

/**
 * 用户-扩展信息(UserExtinfo)Api接口
 *
 * @author Administrator
 * @date 2023/2/8 13:27
 */
public interface UserExtinfoApi {

    /**
     * 添加 用户-扩展信息
     *
     * @param userExtinfoDTO
     */
    Response<String> insertUserExtinfo(UserExtinfoDTO userExtinfoDTO);

    /**
     * 查看 用户-扩展信息
     *
     * @param userId
     */
    Response<UserExtinfoVo> getUserExtinfoByUserId(String userId);

    /**
     * 编辑 用户-扩展信息
     *
     * @param userExtinfoDTO
     */
    Response<String> updateUserExtinfoById(UserExtinfoDTO userExtinfoDTO);

    /**
     * 删除单个 用户-扩展信息
     *
     * @param id
     */
    Response<Boolean> deleteUserExtinfoById(String id);
}
