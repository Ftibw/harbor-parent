package com.whxm.harbor.terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HarborTerminalWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborTerminalWebApplication.class, args);
	}

	/*@Override	extends SpringBootServletInitializer
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HarborTerminalWebApplication.class);
	}*/
}
