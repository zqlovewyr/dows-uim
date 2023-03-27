package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserAddressDTO;
import org.dows.user.api.dto.UserDwellingDTO;
import org.dows.user.api.vo.UserAddressVo;
import org.dows.user.api.vo.UserDwellingVo;

/**
 * 用户-地址信息(UserAddress)Api接口
 *
 * @author Administrator
 * @date 2023/2/8 14:05
 */
public interface UserAddressApi {

    /**
     * 添加 用户-地址信息
     *
     * @param userAddressDTO
     */
    Response<String> insertUserAddress(UserAddressDTO userAddressDTO);

    /**
     * 查询 用户-地址信息
     *
     * @param userId
     */
    Response<UserAddressVo> getUserAddressByUserId(String userId);

    /**
     * 编辑 用户-地址信息
     *
     * @param userAddressDTO
     */
    Response<String> updateUserAddressById(UserAddressDTO userAddressDTO);

    /**
     * 删除单个 用户-地址信息
     *
     * @param id
     */
    Response<Boolean> deleteUserAddressById(String id);
}
