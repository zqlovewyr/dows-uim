package org.dows.user.api.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.framework.api.Response;
import org.dows.user.api.dto.UserInstanceDTO;
import org.dows.user.api.vo.UserInstanceVo;

import java.util.List;

/**
 * 用户-实例信息(UserInstance)Api接口
 *
 * @author jiangxia
 * @since 2023-01-10 13:28:22
 */
public interface UserInstanceApi {
    /**
     *
     * 自定义查询 组成员列表
     *
     * @param userInstanceDTO
     */
    Response<IPage<UserInstanceVo>> userInstanceUnionList(UserInstanceDTO userInstanceDTO);

    /**
     * 添加 用户-实例
     *
     * @param userInstanceDTO
     */
    Response<Long> insertUserInstance(UserInstanceDTO userInstanceDTO);

    /**
     * 编辑 用户-实例
     */
    Response<Long> updateUserInstance(UserInstanceDTO userInstanceDTO);

    /**
     * 查看 用户-实例
     *
     * @param id
     */
    Response<UserInstanceVo> getUserInstanceById(Long id);

    /**
     * 查看 用户-实例
     *
     * @param userInstanceDTO
     */
    Response<List<UserInstanceVo>> getUserInstanceList(UserInstanceDTO userInstanceDTO);

}
