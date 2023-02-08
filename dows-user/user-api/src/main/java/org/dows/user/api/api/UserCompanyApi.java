package org.dows.user.api.api;

import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserCompanyDTO;
import org.dows.user.api.dto.UserExtinfoDTO;

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
}
