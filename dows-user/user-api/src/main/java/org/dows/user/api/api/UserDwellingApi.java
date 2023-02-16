package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserDwellingDTO;
import org.dows.user.api.vo.UserDwellingVo;

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

    /**
     * 查询 用户-住所信息
     *
     * @param principalId
     */
    Response<UserDwellingVo> getUserDwellingByPrincipalId(String principalId);

    /**
     * 编辑 用户-家住所信息
     *
     * @param userDwellingDTO
     */
    Response<String> updateUserDwellingById(UserDwellingDTO userDwellingDTO);

    /**
     * 删除单个 用户-家住所信息
     *
     * @param familyId
     */
    Response<Boolean> deleteUserDwellingById(String familyId);
}
