package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dows.account.crud.entity.AccountOrganization;
import org.dows.account.pojo.form.GroupListQuery;
import org.dows.account.pojo.form.GroupUserQuery;
import org.dows.account.pojo.vo.AccountGroupVo;
import org.dows.account.pojo.vo.AccountVo;

/**
 * 账号-组织架构维度信息(AccountOrganization)
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:53:35
 */
@Mapper
public interface AccountOrganizationMapper extends BaseMapper<AccountOrganization> {
    @Select("@AccountOrganizationMapper.queryAccountByGroup")
    IPage<AccountVo> queryAccountByGroup(IPage<AccountVo> page,
                                         @Param("p") GroupUserQuery groupUserQuery);

    @Select("@AccountOrganizationMapper.queryGroupList")
    IPage<AccountGroupVo> queryGroupList(Page<AccountGroupVo> pageInfo, @Param("p") GroupListQuery groupListQuery);
}
