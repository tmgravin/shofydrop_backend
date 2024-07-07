package com.shofydrop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.shofydrop.entity")
public class ShofyDropApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShofyDropApplication.class, args);
	}

}
