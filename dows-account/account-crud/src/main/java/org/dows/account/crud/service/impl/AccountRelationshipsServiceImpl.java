package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountRelationships;
import org.dows.account.crud.mapper.AccountRelationshipsMapper;
import org.dows.account.crud.service.AccountRelationshipsService;
import org.springframework.stereotype.Service;

/**
 * 账号-关系维度信息(AccountRelationships)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:34
 */
@Service
public class AccountRelationshipsServiceImpl extends ServiceImpl<AccountRelationshipsMapper, AccountRelationships> implements AccountRelationshipsService {

}
