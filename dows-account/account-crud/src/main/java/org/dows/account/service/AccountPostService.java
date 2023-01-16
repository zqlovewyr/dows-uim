package org.dows.account.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.entity.AccountPost;
import org.dows.account.query.AccountPostQuery;
import org.dows.account.vo.AccountPostVo;
import org.dows.framework.crud.mybatis.MybatisCrudService;


/**
 * 账号-职位(AccountOrg)表服务接口
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
public interface AccountPostService extends MybatisCrudService<AccountPost> {
    /**
     * 分页查询职位
     * @param page
     * @param accountPostQuery
     * @return
     */
    IPage<AccountPostVo> istByPage(IPage<AccountPostVo> page, AccountPostQuery accountPostQuery);

}

