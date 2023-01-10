package org.dows.account.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dows.account.query.AccountInstanceQuery;
import org.dows.account.query.AccountQuery;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.account.vo.AccountVo;
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

    List<AccountInstanceVo> getAccountInstanceByUserNameAndTenantId(@Param("paramQuery")Map<String,Object> param);

    IPage<AccountInstanceResVo> selectAccountInstancePage(IPage<AccountInstanceResVo> page, @Param("accountInstanceQuery") AccountInstanceQuery accountInstanceQuery);
}

