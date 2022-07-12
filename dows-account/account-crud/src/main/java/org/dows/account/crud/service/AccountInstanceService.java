package org.dows.account.crud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dows.account.crud.entity.AccountInstance;
import org.dows.account.pojo.form.UserListQuery;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.account.pojo.vo.SimpleAccountVo;

import java.util.List;


/**
 * 账号-实例维度信息(AccountInstance)表服务接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:25
 */
public interface AccountInstanceService extends IService<AccountInstance> {
    IPage<AccountVo> queryUserList(Page<AccountVo> pageInfo, UserListQuery userListQuery);

    List<SimpleAccountVo> querySimpleUserByIds(List<Long> userIds);

    List<AccountIdDTO> queryAccountIdList(List<String> loginList, Long accountId);
}
