package com.sns.untitled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
//@EnableRedisHttpSession
public class UntitledApplication {

    public static void main(String[] args) {
        SpringApplication.run(UntitledApplication.class, args);
    }

}
