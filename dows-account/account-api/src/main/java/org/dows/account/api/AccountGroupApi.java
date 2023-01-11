package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountGroupDTO;
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
     * 批量创建 账号-组
     *
     * @param accountOrgGroupDTOS
     */
    void batchInsertGroup(List<AccountOrgGroupDTO> accountOrgGroupDTOS);

    /**
     * 自定义查询 账号-组 列表
     *
     * @param accountGroupDto
     */
    Response<IPage<AccountGroupVo>> customAccountGroupList(AccountGroupDTO accountGroupDto);

    /**
     * 插入 账号-组
     * @param accountOrgGroupDTO
     */
    Response<Boolean> insertAccountGroup(AccountOrgGroupDTO accountOrgGroupDTO);

    /**
     * 插入 组-成员
     * @param accountGroupDTO
     */
    Response<Boolean> insertAccountGroupMember(AccountGroupDTO accountGroupDTO);

}
