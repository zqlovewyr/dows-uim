package org.dows.account.biz;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.entity.AccountInstance;
import org.dows.account.entity.AccountUserInfo;
import org.dows.account.query.AccountQuery;
import org.dows.account.service.AccountInstanceService;
import org.dows.account.service.AccountUserInfoService;
import org.dows.account.vo.AccountVo;
import org.dows.framework.api.Response;
import org.dows.framework.api.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AccountPostUserBiz {
    private final AccountUserInfoService accountUserInfoService;

    private final AccountInstanceService accountInstanceService;


    public Response getAccuntListPage(AccountQuery accountQuery){
        // TODO 消费金额和最后下单时间排序
        // TODO 返回客户消费金额、下单时间、订单量
        Page<AccountVo> page = new Page<>(accountQuery.getPage(), accountQuery.getSize());
        IPage<AccountVo> listByPage = accountUserInfoService.getListByPage(page, accountQuery);
        return Response.ok(listByPage);
    }

    public Response getInfoByAccountId(String accountId){
        AccountVo accountVo = new AccountVo();
        AccountInstance accountInstance = accountInstanceService.lambdaQuery()
                .eq(AccountInstance::getAccountId, accountId)
                .one();
        if (accountInstance == null) {
            throw new BaseException(HttpStatus.BAD_REQUEST.value(), "账号不存在");
        }
        BeanUtil.copyProperties(accountInstance, accountVo);
        AccountUserInfo accountUserInfo = accountUserInfoService.lambdaQuery()
                .eq(AccountUserInfo::getAccountId, accountId)
                .one();
        BeanUtil.copyProperties(accountUserInfo, accountVo);
        return Response.ok(accountVo);
    }
}
