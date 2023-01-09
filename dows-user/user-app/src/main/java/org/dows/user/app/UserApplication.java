package org.dows.user.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication(scanBasePackages={"org.dows.*"})
public class UserApplication {
    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class, args);
    }

    @Value("${uim.index}")
    private String index ;

    @RequestMapping("/")
    public String index(){
        return index;
    }

}
