package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountInstance;
import org.dows.account.crud.mapper.AccountInstanceMapper;
import org.dows.account.crud.service.AccountInstanceService;
import org.dows.account.pojo.form.UserListQuery;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.account.pojo.vo.SimpleAccountVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号-实例维度信息(AccountInstance)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:26
 */
@Service
public class AccountInstanceServiceImpl extends ServiceImpl<AccountInstanceMapper, AccountInstance> implements AccountInstanceService {

    @Override
    public IPage<AccountVo> queryUserList(Page<AccountVo> pageInfo, UserListQuery userListQuery) {
        return this.baseMapper.queryUserList(pageInfo, userListQuery);
    }

    @Override
    public List<SimpleAccountVo> querySimpleUserByIds(List<Long> userIds) {
        return this.baseMapper.querySimpleUserByIds(userIds);
    }

    @Override
    public List<AccountIdDTO> queryAccountIdList(List<String> loginList, Long accountId) {
        return this.baseMapper.queryAccountIdList(loginList, accountId);
    }
}
