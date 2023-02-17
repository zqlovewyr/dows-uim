package org.dows.user.api.api;


import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserAddressDTO;
import org.dows.user.api.dto.UserContactDTO;
import org.dows.user.api.vo.UserContactVo;
import org.dows.user.api.vo.UserJobVo;

import java.util.List;

/**
 * 用户-实例信息(UserContact)Api接口
 *
 * @author jiangxia
 * @since 2023-01-10 13:28:22
 */
public interface UserContactApi {

    /**
     * 添加 用户-联系人信息
     *
     * @param userContactDTO
     */
    Response<String> insertUserContact(UserContactDTO userContactDTO);

    /**
     * 查看 用户-联系方式-实例（不分页）
     *
     * @param userContactDTO
     */
    Response<List<UserContactVo>> getUserContactList(UserContactDTO userContactDTO);

    /**
     * 查看 用户-联系人信息
     *
     * @param userId
     */
    Response<UserContactVo> getUserContactByUserId(String userId);

    /**
     * 编辑 用户-联系人信息
     *
     * @param userContactDTO
     */
    Response<String> updateUserContactById(UserContactDTO userContactDTO);

    /**
     * 删除单个 用户-联系人信息
     *
     * @param id
     */
    Response<Boolean> deleteUserContactById(String id);
}
