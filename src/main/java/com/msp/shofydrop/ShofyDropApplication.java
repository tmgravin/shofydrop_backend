package com.msp.shofydrop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.msp.shofydrop.entity")
@EnableJpaRepositories(basePackages = "com.msp.shofydrop.repository")
public class ShofyDropApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShofyDropApplication.class, args);
    }

}
