package com.whxm.harbor.terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HarborTerminalWebApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HarborTerminalWebApplication.class, args);
	}

	/*@Override	extends SpringBootServletInitializer
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HarborTerminalWebApplication.class);
	}*/
}
