package com.whxm.harbor.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@MapperScan("com.whxm.harbor.mapper")
@RestController
public class HarborShopServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborShopServiceApplication.class, args);
	}

	@GetMapping("/info")
	public String info(){
		return "harbor shop service info";
	}
}
