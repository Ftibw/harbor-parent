package com.whxm.harbor.terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.whxm.harbor.common.interceptor")
public class HarborTerminalWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborTerminalWebApplication.class, args);
	}

	/*@Override	extends SpringBootServletInitializer
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HarborTerminalWebApplication.class);
	}*/
}
