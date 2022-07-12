package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountTags;
import org.dows.account.crud.mapper.AccountTagsMapper;
import org.dows.account.crud.service.AccountTagsService;
import org.springframework.stereotype.Service;

/**
 * 账号-标签维度信息(AccountTags)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:35
 */
@Service
public class AccountTagsServiceImpl extends ServiceImpl<AccountTagsMapper, AccountTags> implements AccountTagsService {

}
