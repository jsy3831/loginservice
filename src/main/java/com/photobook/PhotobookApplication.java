package com.photobook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PhotobookApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotobookApplication.class, args);
    }

}
