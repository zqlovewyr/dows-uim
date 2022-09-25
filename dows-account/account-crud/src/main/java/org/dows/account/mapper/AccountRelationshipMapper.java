package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.entity.AccountRelationship;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-关系维度信息(AccountRelationship)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:40
 */
@Mapper
public interface AccountRelationshipMapper extends MybatisCrudMapper<AccountRelationship> {

}
