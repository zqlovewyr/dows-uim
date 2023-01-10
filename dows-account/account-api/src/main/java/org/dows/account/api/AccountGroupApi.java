package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.dto.AccountGroupInfoDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.vo.AccountGroupVo;
import org.dows.framework.api.Response;

import java.util.List;

/**
 * 账号-账号组维度信息(AccountGroup)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:22
 */
public interface AccountGroupApi {

    /**
     * batch insert account-group
     * 批量创建 账号-组
     *
     * @param accountOrgGroupDTOS account-groups
     */
    void batchInsertGroup(List<AccountOrgGroupDTO> accountOrgGroupDTOS);

    /**
     * 自定义查询 账号-组联合负责人 列表
     *
     * @param accountGroupDto
     * @param accountGroupInfoDto
     * @param pageNo
     * @param pageSize
     */
    Response<IPage<AccountGroupVo>> accountGroupUnionList(AccountGroupDTO accountGroupDto, AccountGroupInfoDTO accountGroupInfoDto, Integer pageNo, Integer pageSize);

    /**
     * 自定义查询 账号-组 列表
     *
     * @param accountGroupDto
     * @param pageNo
     * @param pageSize
     */
    Response<IPage<AccountGroupVo>> customAccountGroupList(AccountGroupDTO accountGroupDto, Integer pageNo, Integer pageSize);
}
