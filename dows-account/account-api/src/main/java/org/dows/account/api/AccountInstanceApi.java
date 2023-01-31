package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.dto.AccountInstanceDTO;
import org.dows.account.vo.AccountGroupVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

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


    Response<List<AccountInstanceDTO>> getAccountInstanceDTOListByFile(MultipartFile file, String appId, Long rbacRoleId, String accountOrgOrgId, String password, String avatar, String source, String phone);


    void batchCreateAccountInstance(List<AccountInstanceDTO> accountInstanceDTOList);

    void batchRegister(MultipartFile file, String appId, Long rbacRoleId, String accountOrgOrgId, String password, String avatar, String source, String phone);

    /**
     * 登录 账号-实例
     *
     */
    Response<Map<String, Object>> login(AccountInstanceDTO accountInstanceDTO);

    /**
     * 查看 账号-实例-列表
     */
    Response<IPage<AccountInstanceVo>> customAccountInstanceList(AccountInstanceDTO accountInstanceDTO);

}
