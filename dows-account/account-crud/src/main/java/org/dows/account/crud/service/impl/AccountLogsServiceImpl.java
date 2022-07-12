package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountLogs;
import org.dows.account.crud.mapper.AccountLogsMapper;
import org.dows.account.crud.service.AccountLogsService;
import org.springframework.stereotype.Service;

/**
 * 账号-操作记录维度信息(AccountLogs)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:27
 */
@Service
public class AccountLogsServiceImpl extends ServiceImpl<AccountLogsMapper, AccountLogs> implements AccountLogsService {

}
