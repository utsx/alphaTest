package com.alpha.utsx;

import feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UtsxApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtsxApplication.class, args);
	}

}
