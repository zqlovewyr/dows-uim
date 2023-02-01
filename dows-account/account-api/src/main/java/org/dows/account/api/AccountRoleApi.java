package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountRoleDTO;
import org.dows.account.vo.AccountRoleVo;
import org.dows.framework.api.Response;

/**
 * 账号-角色(AccountRole)Api接口
 *
 * @author Administrator
 * @since 2023-09-25 09:25:22
 */
public interface AccountRoleApi {

    /**
     * 自定义查询 账号-角色 列表
     *
     * @param accountRoleDTO
     */
    Response<IPage<AccountRoleVo>> customAccountRoleList(AccountRoleDTO accountRoleDTO);

    /**
     * 查看 角色-信息
     *
     * @param id
     */
    Response<AccountRoleVo> getAccountRoleById(Long id);

    /**
     * 编辑 角色-信息
     *
     * @param accountRoleDTO
     */
    Response<Boolean> updateAccountRoleById(AccountRoleDTO accountRoleDTO);

    /**
     * 授权 账号-角色
     *
     * @param accountRoleDTO
     */
    Response<Long> authAccountRole(AccountRoleDTO accountRoleDTO);
}
