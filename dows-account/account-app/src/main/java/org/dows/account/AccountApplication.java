package org.dows.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(scanBasePackages = {"org.dows.account.*","org.dows.rbac.*","org.dows.user.*","org.dows.framework.crud.*"})
@MapperScan(basePackages = {"org.dows.*.mapper"})
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Value("${uim.index}")
    private String index ;

    @RequestMapping("/")
    public String index(){
        return index;
    }
}
