package org.dows.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.dows.*")
@MapperScan(basePackages = "org.dows.*")
public class UimApplication {
    public static void main(String[] args) {
        SpringApplication.run(UimApplication.class, args);
    }
}
