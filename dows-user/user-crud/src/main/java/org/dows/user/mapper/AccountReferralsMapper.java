package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.AccountReferrals;

/**
 * 推荐关系(AccountReferrals)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:28
 */
@Mapper
public interface AccountReferralsMapper extends MybatisCrudMapper<AccountReferrals> {

}

