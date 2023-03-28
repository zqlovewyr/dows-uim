package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserCompanyDTO;
import org.dows.user.api.dto.UserExtinfoDTO;
import org.dows.user.api.vo.UserCompanyVo;
import org.dows.user.api.vo.UserJobVo;

/**
 * 用户-公司信息(UserCompany)Api接口
 *
 * @author Administrator
 * @date 2023/2/8 13:27
 */
public interface UserCompanyApi {

    /**
     * 添加 用户-公司信息
     *
     * @param userCompanyDTO
     */
    Response<String> insertUserCompany(UserCompanyDTO userCompanyDTO);

    /**
     * 查看 用户-公司信息
     *
     * @param userId
     */
    Response<UserCompanyVo> getUserCompanyByUserId(String userId);

    /**
     * 查看 用户-公司信息
     *
     * @param userCompanyDTO
     */
    Response<UserCompanyVo> getUserCompany(UserCompanyDTO userCompanyDTO);

    /**
     * 编辑 用户-公司信息
     *
     * @param userCompanyDTO
     */
    Response<String> updateUserCompanyById(UserCompanyDTO userCompanyDTO);

    /**
     * 删除单个 用户-公司信息
     *
     * @param id
     */
    Response<Boolean> deleteUserCompanyById(String id);
}
