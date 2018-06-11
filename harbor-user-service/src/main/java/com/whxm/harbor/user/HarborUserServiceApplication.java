package com.whxm.harbor.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whxm.harbor.mapper")
public class HarborUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborUserServiceApplication.class, args);
	}
}
