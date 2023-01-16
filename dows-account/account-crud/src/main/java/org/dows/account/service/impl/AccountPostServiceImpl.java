package org.dows.account.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.dows.account.entity.AccountPost;
import org.dows.account.mapper.AccountPostMapper;
import org.dows.account.query.AccountPostQuery;
import org.dows.account.service.AccountPostService;
import org.dows.account.vo.AccountPostVo;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 账号-职位(AccountOrg)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Service("accountPostService")
@RequiredArgsConstructor
public class AccountPostServiceImpl extends MybatisCrudServiceImpl<AccountPostMapper, AccountPost> implements AccountPostService {


    private final AccountPostMapper accountPostMapper;

    @Override
    public IPage<AccountPostVo> istByPage(IPage<AccountPostVo> page, AccountPostQuery accountPostQuery) {
        return accountPostMapper.selectAccountPostPage(page,accountPostQuery);
    }
}

