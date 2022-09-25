package org.dows.account.service.impl;


import org.dows.account.entity.AccountInstance;
import org.dows.account.mapper.AccountInstanceMapper;
import org.dows.account.service.AccountInstanceService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 账号-实例维度信息(AccountInstance)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:27
 */
@Service
public class AccountInstanceServiceImpl extends MybatisCrudServiceImpl<AccountInstanceMapper, AccountInstance> implements AccountInstanceService {

}
