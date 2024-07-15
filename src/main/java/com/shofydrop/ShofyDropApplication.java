package com.shofydrop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.shofydrop.entity")
@EnableJpaRepositories(basePackages = "com.shofydrop.repository")
public class ShofyDropApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShofyDropApplication.class, args);
	}

}
