package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dows.account.crud.entity.AccountGroup;

import java.util.List;

/**
 * 账号-账号组维度信息(AccountGroup)
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:53:31
 */
@Mapper
public interface AccountGroupMapper extends BaseMapper<AccountGroup> {


    @Update("@AccountGroupMapper.groupAddAccount")
    void groupAddAccount(AccountGroup accountGroup);

    @Select("select b.*,t.org_id from account_group t left join account_organization b on t.org_id = b.id " +
            "${ew.customSqlSegment}")
    IPage accountGroupList(@Param(Constants.WRAPPER) QueryWrapper<AccountGroup> wrapper);

    @Select("@AccountGroupMapper.countByGroup")
    List countByGroup(@Param("orgIds") List<Long> groupIdList);
}
