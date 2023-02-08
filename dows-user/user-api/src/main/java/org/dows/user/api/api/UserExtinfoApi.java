package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserExtinfoDTO;

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
}
