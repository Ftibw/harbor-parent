package com.whxm.harbor.screensaver.material;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whxm.harbor.mapper")
public class HarborScreensaverMaterialServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborScreensaverMaterialServiceApplication.class, args);
	}
}
