package com.whxm.harbor.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whxm.harbor.mapper")
public class HarborShopServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborShopServiceApplication.class, args);
	}
}
