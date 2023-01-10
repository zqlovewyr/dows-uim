package org.dows.account.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.entity.AccountInstance;
import org.dows.account.query.AccountInstanceQuery;
import org.dows.account.query.AccountQuery;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.account.vo.AccountVo;
import org.dows.framework.crud.mybatis.MybatisCrudService;

import java.util.List;
import java.util.Map;


/**
 * 账号-实例(AccountInstance)表服务接口
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
public interface AccountInstanceService extends MybatisCrudService<AccountInstance> {

    /**
     * 根据userName与TenantId查询账号是否存在
     * @param param
     * @return
     */
    List<AccountInstanceVo> getAccountInstanceByUserNameAndTenantId(Map<String,Object> param);

    /**
     * 分页查询用户基础信息
     * @param page
     * @param accountInstanceQuery
     * @return
     */
    IPage<AccountInstanceResVo> getListByPage(IPage<AccountInstanceResVo> page, AccountInstanceQuery accountInstanceQuery);
}

