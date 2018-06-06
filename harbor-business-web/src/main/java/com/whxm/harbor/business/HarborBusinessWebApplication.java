package com.whxm.harbor.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class HarborBusinessWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborBusinessWebApplication.class, args);
	}
}
