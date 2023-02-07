package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountOrgDTO;
import org.dows.account.dto.TreeAccountOrgDTO;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.api.Response;

import java.util.List;

/**
 * 账号-组织架构维度信息(AccountGroup)Api接口
 *
 * @author VX:PN15855012581
 * @since 2023-01-31 10:03:22
 */
public interface AccountOrgApi {

    /**
     * 创建树形结构 accountOrg 账号-组织
     *
     * @param treeAccountOrgDto recursion TreeDto
     */
    void createTreeAccountOrg(TreeAccountOrgDTO treeAccountOrgDto);

    /**
     * create account-org with account-group
     *
     * @param accountOrgDTO single accountOrgDTO
     * @return AccountOrgVo
     */
    Response<Long> createAccountOrg(AccountOrgDTO accountOrgDTO);


    IPage<AccountOrgVo> teacherPageAccountOrg(String accountId, String appId, Integer pageNo, Integer pageSize);

    IPage<AccountOrgVo> adminPageAccountOrg(String appId, Integer pageNo, Integer pageSize);

    /**
     *
     * 自定义查询 机构-实例 分页列表
     *
     * @param accountOrgDTO
     */
    Response<IPage<AccountOrgVo>> customAccountOrgList(AccountOrgDTO accountOrgDTO);

    /**
     * 更新 机构-实例
     *
     * @param accountOrgDTO single accountOrgDTO
     * @return AccountOrgVo
     */
    void updateAccountOrgById(AccountOrgDTO accountOrgDTO);

    /**
     * 查看 机构-实例
     *
     * @param id
     */
    Response<AccountOrgVo> getAccountOrgById(String id);

    /**
     * 删除 机构-实例
     *
     * @param id
     */
    void deleteAccountOrgById(Long id);

    /**
     * 批量删除 组织架构
     *
     * @param ids
     */
    void batchDeleteAccountOrgs(List<String> ids);
}
