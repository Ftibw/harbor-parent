package com.whxm.harbor.floor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.whxm.harbor.common.interceptor")
public class HarborFloorWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarborFloorWebApplication.class, args);
    }

	/*@Override	extends SpringBootServletInitializer
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HarborFloorWebApplication.class);
	}*/
}
