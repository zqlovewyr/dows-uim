package org.dows.user.biz;

import lombok.Builder;
import lombok.Data;

/**
 * Author:      gfl
 * Mail:        fuleiit@163.com
 * Date:        2023/2/23 20:36
 * Version:     1.0
 * Description: 
 */
@Data
@Builder
public class UserCompanyRequest {

    private String certNo;

    private String companyName;

    private String legalPerson;
}
