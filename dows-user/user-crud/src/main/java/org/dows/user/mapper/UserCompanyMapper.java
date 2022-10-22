package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.UserCompany;

/**
 * 用户-公司(UserCompany)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:11
 */
@Mapper
public interface UserCompanyMapper extends MybatisCrudMapper<UserCompany> {

}

