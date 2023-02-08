package org.dows.user.api.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
     * 查询 用户-家庭
     *
     * @param userId
     */
    Response<UserFamilyVo> getUserFamilyByUserId(String userId);

    /**
     * 新增 用户-家庭
     *
     * @param userFamilyDTO
     */
    Response<String> insertUserFamily(UserFamilyDTO userFamilyDTO);

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

    /**
     * 获取 用户-家庭-户主-列表
     *
     * @param userFamilyDTO
     */
    Response<IPage<UserFamilyVo>> getFamilyArchivesList(UserFamilyDTO userFamilyDTO);

    /**
     * 获取 用户-家庭-成员-列表
     *
     * @param userFamilyDTO
     */
    Response<IPage<UserFamilyVo>> getFamilyMemberList(UserFamilyDTO userFamilyDTO);
}
