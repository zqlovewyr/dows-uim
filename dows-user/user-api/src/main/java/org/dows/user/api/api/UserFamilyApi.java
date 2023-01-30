package org.dows.user.api.api;

import org.dows.user.api.vo.UserFamilyVo;

/**
 * 用户-家庭信息(UserFamily)Api接口
 *
 * @author jiangxia
 * @since 2023-01-30 13:28:22
 */
public interface UserFamilyApi {
    /**
     * 查询 族谱关系
     *
     * @param userId
     */
    UserFamilyVo getGenealogyList(String userId);
}
