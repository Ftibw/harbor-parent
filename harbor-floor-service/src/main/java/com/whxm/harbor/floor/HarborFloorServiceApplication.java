package com.whxm.harbor.floor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whxm.harbor.mapper")
public class HarborFloorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborFloorServiceApplication.class, args);
	}
}
