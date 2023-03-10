package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountIdentifierApi;
import org.dows.account.entity.AccountIdentifier;
import org.dows.account.service.AccountIdentifierService;
import org.dows.account.vo.AccountIdentifierSearchVO;
import org.dows.account.vo.AccountIdentifierVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountIdentifierBiz implements AccountIdentifierApi {
    private final AccountIdentifierService accountIdentifierService;

    @Override
    public List<AccountIdentifierVO> searchIdentifier(AccountIdentifierSearchVO searchVO) {
        Set<String> accountIds = searchVO.getAccountIds();
        String appId = searchVO.getAppId();
        LambdaQueryWrapper<AccountIdentifier> wrapper = new LambdaQueryWrapper<>();
        if (!CollectionUtils.isEmpty(accountIds)) {
            wrapper.in(AccountIdentifier::getAccountId, accountIds);
        }
        if (StringUtils.hasText(appId)) {
            wrapper.eq(AccountIdentifier::getAppId, appId);
        }
        List<AccountIdentifier> list = accountIdentifierService.list(wrapper);
        List<AccountIdentifierVO> res = list.stream().map(e -> AccountIdentifierVO.builder()
            .identifier(e.getIdentifier())
            .accountId(e.getAccountId())
            .appId(e.getAppId())
            .build()
        ).collect(Collectors.toList());
        return res;
    }

    @Override
    public boolean removeByAccountIds(Set<String> accountIds) {
        if (null != accountIds && !accountIds.isEmpty()) {
            return accountIdentifierService.lambdaUpdate().in(AccountIdentifier::getAccountId, accountIds).remove();
        }
        return false;
    }
}
