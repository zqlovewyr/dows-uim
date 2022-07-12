package org.dows.account.crud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dows.account.crud.entity.AccountIdentifier;

/**
 * 账号-标识(AccountIdentifier)表服务接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:24
 */
public interface AccountIdentifierService extends IService<AccountIdentifier> {

    int updateAccountIdentifier(AccountIdentifier accountIdentifier);
}
