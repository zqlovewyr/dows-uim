package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountGroupInfo;

/**
 * 账号-组(AccountGroupInfo)表数据库访问层
 *
 * @author lait
 * @since 2023-01-09 15:01:25
 */
@Mapper
public interface AccountGroupInfoMapper extends MybatisCrudMapper<AccountGroupInfo> {

}

