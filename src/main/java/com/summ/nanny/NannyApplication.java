package com.summ.nanny;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.summ.nanny.mapper")
public class NannyApplication {
    public static void main(String[] args) {
        SpringApplication.run(NannyApplication.class, args);
    }
}
