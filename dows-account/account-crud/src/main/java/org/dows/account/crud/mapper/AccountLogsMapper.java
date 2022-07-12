package org.dows.account.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dows.account.crud.entity.AccountLogs;

/**
 * 账号-操作记录维度信息(AccountLogs)
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:27
 */
@Mapper
public interface AccountLogsMapper extends BaseMapper<AccountLogs> {

}
