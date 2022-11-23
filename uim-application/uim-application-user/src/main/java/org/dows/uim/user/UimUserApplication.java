package org.dows.uim.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"org.dows.*"})
public class UimUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UimUserApplication.class, args);
    }
}
