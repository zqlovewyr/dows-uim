package org.dows.account.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dows.account.entity.AccountOrg;
import org.dows.account.query.AccountOrgQuery;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-组织架构(AccountOrg)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Mapper
public interface AccountOrgMapper extends MybatisCrudMapper<AccountOrg> {

    IPage<AccountOrgVo> selectAccountOrgPage(IPage<AccountOrgVo> page, @Param("accountOrgQuery") AccountOrgQuery accountOrgQuery);

}

