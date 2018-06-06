package com.whxm.harbor.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whxm.harbor.mapper")
public class HarborActivityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborActivityServiceApplication.class, args);
	}
}
