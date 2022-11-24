package org.dows.uim.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"org.dows.*"})
public class UimAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(UimAdminApplication.class, args);
    }
}
