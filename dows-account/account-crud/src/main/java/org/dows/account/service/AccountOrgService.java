package org.dows.account.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.entity.AccountOrg;
import org.dows.account.query.AccountOrgQuery;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.crud.mybatis.MybatisCrudService;


/**
 * 账号-组织架构(AccountOrg)表服务接口
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
public interface AccountOrgService extends MybatisCrudService<AccountOrg> {
    /**
     * 分页查询组织架构
     * @param page
     * @param accountOrgQuery
     * @return
     */
    IPage<AccountOrgVo> getListByPage(IPage<AccountOrgVo> page, AccountOrgQuery accountOrgQuery);

}

