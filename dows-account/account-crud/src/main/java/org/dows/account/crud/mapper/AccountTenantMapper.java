package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dows.account.crud.entity.AccountTenant;
import org.dows.account.pojo.form.AccountTenantRelQuery;
import org.dows.account.pojo.query.AccountOwnerQuery;
import org.dows.account.pojo.vo.AccountVo;

import java.util.List;

/**
 * 账号-租户维度信息(AccountTenant)
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:53:37
 */
@Mapper
public interface AccountTenantMapper extends BaseMapper<AccountTenant> {


    @Select("@AccountTenantMapper.queryAccountTenant")
    IPage<AccountVo> queryAccountTenant(IPage<AccountVo> page,
                                        @Param("p") AccountTenantRelQuery accountTenantRelQuery);


    @Select("@AccountTenantMapper.queryAccountTenantList")
    IPage<TenantVo> queryAccountTenantList(IPage<TenantVo> page,
                                           @Param("p") AccountOwnerQuery accountOwnerQuery);


    @Select("@AccountTenantMapper.countAccountNum")
    List<CountDTO> countAccountNum(@Param("tenantIds") List<Long> tenantIds);

    @Select("@AccountTenantMapper.countGroupNum")
    List<CountDTO> countGroupNum(@Param("tenantIds") List<Long> tenantIds);

    @Update("@AccountTenantMapper.updateAccountTenant")
    int updateAccountTenant(AccountTenant accountTenant);
}
