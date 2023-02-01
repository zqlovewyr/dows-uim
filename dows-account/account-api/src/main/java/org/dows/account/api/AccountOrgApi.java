package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountOrgDTO;
import org.dows.account.dto.TreeAccountOrgDTO;
import org.dows.account.vo.AccountOrgVo;
import org.springframework.web.bind.annotation.RequestBody;

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
    AccountOrgVo createAccountOrg(AccountOrgDTO accountOrgDTO);


    IPage<AccountOrgVo> teacherPageAccountOrg(String accountId, String appId, Integer pageNo, Integer pageSize);

    IPage<AccountOrgVo> adminPageAccountOrg(String appId, Integer pageNo, Integer pageSize);
}
