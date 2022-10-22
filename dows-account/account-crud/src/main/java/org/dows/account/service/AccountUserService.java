package org.dows.account.service;

import org.dows.framework.crud.mybatis.MybatisCrudService;
import org.dows.account.entity.AccountUser;


/**
 * 账号-用户(实名认证后)(AccountUser)表服务接口
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:30
 */
public interface AccountUserService extends MybatisCrudService<AccountUser> {

}

