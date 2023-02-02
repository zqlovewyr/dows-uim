package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserFamilyDTO;
import org.dows.user.api.vo.UserFamilyVo;

import java.util.List;

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
    Response<UserFamilyVo> getGenealogyList(String userId);

    /**
     * 新增 用户-家庭
     *
     * @param userFamilyDTO
     */
    Response<Long> insertUserFamily(UserFamilyDTO userFamilyDTO);

    /**
     * 编辑 用户-家庭
     *
     * @param userFamilyDTO
     */
    Response<Long> updateUserFamilyById(UserFamilyDTO userFamilyDTO);

    /**
     * 删除 用户-家庭
     *
     * @param ids
     */
    void batchDeleteUserFamilys(List<String> ids);
}
