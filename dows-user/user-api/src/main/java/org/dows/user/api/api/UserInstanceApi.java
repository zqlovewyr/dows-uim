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
    Response<String> insertUserInstance(UserInstanceDTO userInstanceDTO);

    /**
     * 编辑 用户-实例
     */
    Response<String> updateUserInstance(UserInstanceDTO userInstanceDTO);

    /**
     * 查看 用户-实例
     *
     * @param id
     */
    Response<UserInstanceVo> getUserInstanceById(String id);

    /**
     * 查看 用户-实例
     *
     * @param userInstanceDTO
     */
    Response<List<UserInstanceVo>> getUserInstanceFilterList(UserInstanceDTO userInstanceDTO);

    /**
     * 删除单个 账号-组-实例
     *
     * @param id
     */
    Response<Boolean> deleteUserInstanceById(String id);

    /**
     * 批量删除 账号-组-实例
     *
     * @param ids
     */
    void deleteUserInstances(List<String> ids);

    /**
     * 查询 用户-实例 列表(不带分页)
     *
     * @param userInstanceDTO
     */
    Response<List<UserInstanceVo>> getUserInstanceListNoPage(UserInstanceDTO userInstanceDTO);
}
