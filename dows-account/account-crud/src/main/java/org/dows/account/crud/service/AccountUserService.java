package org.dows.account.crud.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dows.account.api.dto.UserContactDto;
import org.dows.account.crud.entity.AccountUser;

import java.util.List;

/**
 * 账号-用户维度信息(AccountUser)表服务接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:37
 */
public interface AccountUserService extends IService<AccountUser> {
    List<UserContactDto> queryUserContacts(QueryWrapper<AccountUser> wrapper);
}
