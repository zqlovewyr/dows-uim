package org.dows.account;

import org.dows.rbac.api.RbacRoleApi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.dows.account.*","org.dows.rbac.*","org.dows.user.*","org.dows.framework.crud.*"})
@MapperScan(basePackages = {"org.dows.*.mapper"})
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
