package org.dows.account.service;

import org.dows.account.entity.AccountAddress;
import org.dows.account.entity.AccountUser;
import org.dows.framework.crud.mybatis.MybatisCrudService;


/**
 * 账号-地址(实名认证后)(AccountAddress)表服务接口
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
public interface AccountAddressService extends MybatisCrudService<AccountAddress> {

}

