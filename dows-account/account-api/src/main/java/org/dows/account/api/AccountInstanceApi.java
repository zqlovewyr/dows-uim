package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountInstanceDTO;
import org.dows.account.vo.AccountInstanceSearchVO;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.Response;
import org.dows.framework.api.exceptions.BizException;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * 账号-实例维度信息(AccountInstance)Api接口
 *
 * @author VX:PN15855012581
 * @since 2023-01-30 09:36:28
 */
public interface AccountInstanceApi {

    /**
     * runsix method process
     * 1.check whether accountIdentifier queried by appId & identifier exist
     * 2.check whether rbacRoleId exist
     * 3.check whether accountOrgOrgId exist
     * 4.save accountIdentifier
     * 5.save accountInstance
     * 6.save accountRole if rbacRoleId exist
     * 7.save accountGroup if orgId exist
     * 8.convert entity to vo and return
     */
    Response<AccountInstanceVo> createAccountInstance(AccountInstanceDTO accountInstanceDTO);

    Response<List<AccountInstanceDTO>> getAccountInstanceDTOListByFile(MultipartFile file, String appId, Long rbacRoleId, String accountOrgOrgId, String password, String avatar,
        String source, String phone);

    void batchCreateAccountInstance(List<AccountInstanceDTO> accountInstanceDTOList);

    void batchRegister(MultipartFile file, String appId, Long rbacRoleId, String accountOrgOrgId, String password, String avatar, String source, String phone);

    /**
     * 登录 账号-实例s
     *
     * @param accountInstanceDTO
     */
    Response login(AccountInstanceDTO accountInstanceDTO);

    /**
     * 查询 账号-实例-列表
     *
     * @param accountInstanceDTO
     */
    Response<IPage<AccountInstanceVo>> customAccountInstanceList(AccountInstanceDTO accountInstanceDTO);

    /**
     * 编辑 账号-实例
     *
     * @param accountInstanceDTO
     */
    Response updateAccountInstanceById(AccountInstanceDTO accountInstanceDTO);

    /**
     * 查询 账户-实例-列表(不带分页)
     */
    Response<List<AccountInstanceVo>> getAccountInstanceList(AccountInstanceDTO accountInstanceDTO);

    /**
     * 删除 账号-实例
     *
     * @param id
     */
    Response<Boolean> deleteAccountInstanceById(Long id);

    /**
     * 批量删除 账号-实例
     *
     * @param ids
     */
    void batchDeleteAccountInstances(List<String> ids);

    /**
     * 重置密码 账号-实例
     *
     * @param accountInstanceDTO
     */
    Response<Boolean> resetPwd(AccountInstanceDTO accountInstanceDTO);

    /**
     * 查看 账号-实例
     *
     * @param id
     */
    Response<AccountInstanceVo> getAccountInstanceById(String id);

    Response<Boolean> updateAccountInstance(AccountInstanceDTO dto);

    Response<Boolean> batchResetPwd(AccountInstanceDTO accountInstanceDTO);

    /**
     * @author zhuchenmin
     */
    Response<IPage<AccountInstanceVo>> searchAccountInstance(AccountInstanceSearchVO vo, Long pageNo, Long pageSize, LinkedHashMap<String, Boolean> columnOrderMap);

    /**
     * @author zhuchenmin
     */
    Response<Boolean> removeAccountByAccountIds(Set<String> accountIds) throws BizException;

    /**
     * 创建用户实例(重新使用 @author runsix 的业务代码)
     */
    Response<AccountInstanceVo> createAccountW(AccountInstanceDTO dto);
}