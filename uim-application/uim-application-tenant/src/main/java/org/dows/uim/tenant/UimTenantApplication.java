package org.dows.uim.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"org.dows.*"})
public class UimTenantApplication {
    public static void main(String[] args) {
        SpringApplication.run(UimTenantApplication.class, args);
    }
}
