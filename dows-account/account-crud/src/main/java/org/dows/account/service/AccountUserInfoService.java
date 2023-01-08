package org.dows.account.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.entity.AccountUserInfo;
import org.dows.account.query.AccountQuery;
import org.dows.account.vo.AccountVo;
import org.dows.framework.crud.mybatis.MybatisCrudService;

public interface AccountUserInfoService extends MybatisCrudService<AccountUserInfo>  {
    IPage<AccountVo> getListByPage(IPage<AccountVo> page, AccountQuery accountQuery);
}
