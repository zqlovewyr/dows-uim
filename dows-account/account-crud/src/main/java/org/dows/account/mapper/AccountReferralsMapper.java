package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountReferrals;

/**
 * 推荐关系(AccountReferrals)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@Mapper
public interface AccountReferralsMapper extends MybatisCrudMapper<AccountReferrals> {

}

