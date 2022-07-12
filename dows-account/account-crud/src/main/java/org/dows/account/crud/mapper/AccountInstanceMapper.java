package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dows.account.crud.entity.AccountInstance;
import org.dows.account.pojo.form.UserListQuery;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.account.pojo.vo.SimpleAccountVo;

import java.util.List;

/**
 * 账号-实例维度信息(AccountInstance)
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:53:33
 */
@Mapper
public interface AccountInstanceMapper extends BaseMapper<AccountInstance> {

    @Select("@AccountInstanceMapper.queryUserList")
    IPage<AccountVo> queryUserList(IPage<AccountVo> page, @Param("bean") UserListQuery userListQuery);

    @Select("@AccountInstanceMapper.queryAccountIdList")
    List<AccountIdDTO> queryAccountIdList(@Param("loginList") List<String> loginList, @Param("accountId") Long accountId);

    @Select("@AccountInstanceMapper.querySimpleUserByIds")
    List<SimpleAccountVo> querySimpleUserByIds(@Param("userIds") List<Long> userIds);
}
