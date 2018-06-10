package com.whxm.harbor.activity.material;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whxm.harbor.mapper")
public class HarborActivityMaterialServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborActivityMaterialServiceApplication.class, args);
	}
}
