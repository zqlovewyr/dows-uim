//package org.dows.account.biz;
//
//import cn.hutool.core.bean.BeanUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.dows.account.entity.AccountIdentifier;
//import org.dows.account.entity.AccountInstance;
//import org.dows.account.service.AccountIdentifierService;
//import org.dows.account.service.AccountInstanceService;
//import org.dows.framework.api.Response;
//import org.springframework.stereotype.Service;
//
//
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class ZLAccountBiz {
//
//    private final AccountIdentifierService accountIdentifierService;
//    private final AccountInstanceService accountInstanceService;
//
//
//    public Response createAccount(ZLCreateAccountRequest zLCreateAccountRequest){
//        AccountIdentifier  accountIdentifier = accountIdentifierService.lambdaQuery()
//                .eq(AccountIdentifier::getIdentifier, zLCreateAccountRequest.getIdentifier())
//                .eq(AccountIdentifier::getAppId, zLCreateAccountRequest.getAppId())
//                .oneOpt()
//                .orElse(null);
//
//        if(accountIdentifier != null){
//            throw new ZLAccountException(ZLAccountStatusCode.ACCOUNT_EXESIT_EXCPETION);
//        }
//        accountIdentifier = new AccountIdentifier();
//        BeanUtil.copyProperties(zLCreateAccountRequest,accountIdentifier);
//
//        accountIdentifierService.save(accountIdentifier);
//
//        AccountInstance accountInstance = new AccountInstance();
//
//        accountInstanceService.save(accountInstance);
//        return Response.ok();
//    }
//}
