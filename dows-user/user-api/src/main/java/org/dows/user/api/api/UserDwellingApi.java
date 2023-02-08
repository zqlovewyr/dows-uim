package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserDwellingDTO;

/**
 * 用户-住所信息(UserDwelling)Api接口
 *
 * @author Administrator
 * @date 2023/2/8 14:05
 */
public interface UserDwellingApi {

    /**
     * 添加 用户-住所信息
     *
     * @param userDwellingnDTO
     */
    Response<String> insertUserDwelling(UserDwellingDTO userDwellingnDTO);
}
