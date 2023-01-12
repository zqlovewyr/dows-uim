package org.dows.uim.user;

import org.dows.account.mapper.AccountGroupMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages={"org.dows.account"})
public class UimUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UimUserApplication.class, args);
    }
}
