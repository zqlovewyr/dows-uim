package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dows.account.api.dto.UserContactDto;
import org.dows.account.crud.entity.AccountUser;

import java.util.List;

/**
 * 账号-用户维度信息(AccountUser)
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:37
 */
@Mapper
public interface AccountUserMapper extends BaseMapper<AccountUser> {
    @Select("select t.*,p.* from account_user t left join user_contact p on t.user_id = p.user_id " +
            "${ew.customSqlSegment}")
    List<UserContactDto> queryUserContacts(@Param(Constants.WRAPPER) QueryWrapper<AccountUser> wrapper);

}
