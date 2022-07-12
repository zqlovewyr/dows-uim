package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountRelationships;

/**
 * 账号-关系维度信息(AccountRelationships)
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:34
 */
@Mapper
public interface AccountRelationshipsMapper extends BaseMapper<AccountRelationships> {

}
