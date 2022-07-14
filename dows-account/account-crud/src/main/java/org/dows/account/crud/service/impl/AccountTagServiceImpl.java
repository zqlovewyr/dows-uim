package org.dows.account.crud.service.impl;


import org.dows.account.crud.entity.AccountTag;
import org.dows.account.crud.mapper.AccountTagMapper;
import org.dows.account.crud.service.AccountTagService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 账号-标签维度信息(AccountTag)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:43
 */
@Service
public class AccountTagServiceImpl extends MybatisCrudServiceImpl<AccountTagMapper, AccountTag> implements AccountTagService {

}
