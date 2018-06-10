package com.whxm.harbor.terminal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whxm.harbor.mapper")
public class HarborTerminalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborTerminalServiceApplication.class, args);
	}
}
