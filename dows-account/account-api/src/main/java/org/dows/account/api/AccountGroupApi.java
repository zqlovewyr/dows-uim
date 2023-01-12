package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.vo.AccountGroupVo;
import org.dows.framework.api.Response;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 账号-账号组维度信息(AccountGroup)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:22
 */
public interface AccountGroupApi {

    /**
     * 批量添加 账号-组-成员
     *
     * @param accountOrgGroupDTOS
     */
    Response<Boolean> batchInsertGroup(List<AccountOrgGroupDTO> accountOrgGroupDTOS);

    /**
     * 自定义查询 账号-组-成员 列表
     *
     * @param accountGroupDTO
     */
    Response<IPage<AccountGroupVo>> customAccountGroupList(AccountGroupDTO accountGroupDTO);

    /**
     * 添加/编辑 账号-组-成员
     *
     * @param accountGroupDTO
     */
    Response<Boolean> insertOrUpdateAccountGroup(AccountGroupDTO accountGroupDTO);

    /**
     * 获取 下载模板
     *
     * @param response
     */
    Response<Object> downloadExcelTemplate(HttpServletResponse response);

    /**
     *
     * 导入成员
     *
     * @param file
     */
    Response<Object> uploadAccountGroup(MultipartFile file);

    /**
     * 批量删除 组-成员
     *
     * @param accountGroupDTOs
     */
    Response<Object> batchDeleteGroupMembers(List<AccountGroupDTO> accountGroupDTOs);
}
