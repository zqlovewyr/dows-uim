package org.dows.user.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.user.entity.UserCompany;
import org.dows.user.service.UserCompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserCompanyBiz {


    private final UserCompanyService userCompanyService;
    public UserCompany getOneUserCompany(UserCompanyRequest userCompanyRequest) {

        return userCompanyService.lambdaQuery()
                .select(UserCompany::getCertNo,UserCompany::getCompanyCode,UserCompany::getId)
                .eq(UserCompany::getCertNo,userCompanyRequest.getCertNo())
                .one();
    }

    public void saveUserCompany(UserCompanyRequest userCompanyRequest) {
        UserCompany userCompany = new UserCompany();
        BeanUtils.copyProperties(userCompanyRequest,userCompany);
        userCompanyService.save(userCompany);
    }
}
