package org.dows.account.service.ext;

import org.dows.account.entity.AccountGroup;
import org.dows.account.mapper.AccountGroupMapper;
import org.dows.account.service.AccountGroupService;
import org.dows.account.service.impl.AccountGroupServiceImpl;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 账号-组(AccountGroup)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:25
 */
@Service("accountGroupServiceExt")
public class AccountGroupServiceExt extends AccountGroupServiceImpl {

}

