package org.dows.rbac.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.framework.api.Response;
import org.dows.rbac.dto.RbacRoleDTO;
import org.dows.rbac.vo.RbacRoleVo;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

/**
 * RBAC-角色(RbacRole)Api接口
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:19
 */
public interface RbacRoleApi {

    Response<RbacRoleVo> getById(String id);

    Response<List<RbacRoleVo>> getByIdList(List<String> rbacRoleIdList);

    Response<List<RbacRoleVo>> getByIdListAndAppId(List<String> rbacRoleIdList, @PathVariable String appid);

    Response<List<RbacRoleVo>> getByIdList(List<String> idList, String appId);

    /**
     * 自定义查询 角色-实例 列表
     *
     * @param rbacRoleDTO
     */
    Response<IPage<RbacRoleVo>> customRbacRoleList(RbacRoleDTO rbacRoleDTO);

}
