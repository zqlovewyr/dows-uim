package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountMembers;

/**
 * 账号-会员维度信息(AccountMembers)
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:28
 */
@Mapper
public interface AccountMembersMapper extends BaseMapper<AccountMembers> {

}
