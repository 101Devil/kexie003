package com.kexie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kexie.mapper")
public class WebrunApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebrunApplication.class, args);
    }

}
