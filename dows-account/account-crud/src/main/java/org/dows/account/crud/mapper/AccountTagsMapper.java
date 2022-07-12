package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountTags;

/**
 * 账号-标签维度信息(AccountTags)
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:53:36
 */
@Mapper
public interface AccountTagsMapper extends BaseMapper<AccountTags> {

}
