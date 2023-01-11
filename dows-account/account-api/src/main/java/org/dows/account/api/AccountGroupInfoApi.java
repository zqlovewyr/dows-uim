package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.dto.AccountGroupInfoDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.vo.AccountGroupInfoVo;
import org.dows.account.vo.AccountGroupVo;
import org.dows.framework.api.Response;

import java.util.List;

/**
 * 账号-账号组信息维度信息(AccountGroupInfo)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:22
 */
public interface AccountGroupInfoApi {

    /**
     *
     * 自定义查询 账号-组负责人 列表
     *
     * @param accountGroupInfoDTO
     */
    Response<IPage<AccountGroupInfoVo>> customAccountGroupInfoList(AccountGroupInfoDTO accountGroupInfoDTO);
}
