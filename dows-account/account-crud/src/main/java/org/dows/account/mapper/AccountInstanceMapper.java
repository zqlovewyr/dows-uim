package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountInstance;

import java.util.List;
import java.util.Map;

/**
 * 账号-实例(AccountInstance)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Mapper
public interface AccountInstanceMapper extends MybatisCrudMapper<AccountInstance> {

    List<AccountInstanceResVo> getAccountInstanceByUserNameAndTenantId(@Param("paramQuery")Map<String,Object> param);
}

