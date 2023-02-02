package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.vo.AccountGroupVo;
import org.dows.account.vo.NormalDataVo;
import org.dows.framework.api.Response;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
     * @param accountGroupDTOs
     */
    Response<Boolean> batchInsertGroup(List<AccountGroupDTO> accountGroupDTOs);

    /**
     * 自定义查询 账号-组-成员 列表
     *
     * @param accountGroupDTO
     */
    Response<IPage<AccountGroupVo>> customAccountGroupList(AccountGroupDTO accountGroupDTO);

    /**
     * 添加 账号-组-成员
     *
     * @param accountGroupDTO
     */
    Response<Map<String,Object>> insertOrUpdateAccountGroup(AccountGroupDTO accountGroupDTO);

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

    /**
     * 查询年龄段占比 组-成员
     *
     * @param accountGroupDTO
     * @return
     */
    Response<List<NormalDataVo>> getAgeRateList(AccountGroupDTO accountGroupDTO);

    /**
     * 查看 组-成员-列表（不分页）
     *
     * @param accountGroupDTO
     */
    Response<List<AccountGroupVo>> getAccountGroupList(AccountGroupDTO accountGroupDTO);

    /**
     * 编辑 账号-组-成员
     *
     * @param accountGroupDTO
     */
    Response<Map<String,Object>> updateAccountGroupById(AccountGroupDTO accountGroupDTO);
}
