package org.dows.account.feign;

import org.dows.account.api.AccountLogsApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-操作记录维度信息(AccountLogs)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:24
 */

@FeignClient(name = "cop-account", contextId = "accountLogs", path = "/accountLogs")
public interface AccountLogsFeign extends AccountLogsApi {


}
