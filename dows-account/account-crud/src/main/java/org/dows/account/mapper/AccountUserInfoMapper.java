package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.entity.AccountUserInfo;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

@Mapper
public interface AccountUserInfoMapper extends MybatisCrudMapper<AccountUserInfo> {
}
