package org.dows.account.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dows.account.entity.AccountPost;
import org.dows.account.query.AccountPostQuery;
import org.dows.account.vo.AccountPostVo;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-职位(AccountOrg)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Mapper
public interface AccountPostMapper extends MybatisCrudMapper<AccountPost> {

    IPage<AccountPostVo> selectAccountPostPage(IPage<AccountPostVo> page, @Param("accountPostQuery") AccountPostQuery accountPostQuery);

}

