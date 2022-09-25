package org.dows.account.service.impl;


import org.dows.account.entity.AccountRelationship;
import org.dows.account.mapper.AccountRelationshipMapper;
import org.dows.account.service.AccountRelationshipService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 账号-关系维度信息(AccountRelationship)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:40
 */
@Service
public class AccountRelationshipServiceImpl extends MybatisCrudServiceImpl<AccountRelationshipMapper, AccountRelationship> implements AccountRelationshipService {

}
