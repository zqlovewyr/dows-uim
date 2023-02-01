package org.dows.user.api.api;


import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserContactDTO;
import org.dows.user.api.vo.UserContactVo;
import java.util.List;

/**
 * 用户-实例信息(UserContact)Api接口
 *
 * @author jiangxia
 * @since 2023-01-10 13:28:22
 */
public interface UserContactApi {

    /**
     * 查看 用户-联系方式-实例（不分页）
     *
     * @param userContactDTO
     */
    Response<List<UserContactVo>> getUserContactList(UserContactDTO userContactDTO);

}
