package com.whxm.harbor.floor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class HarborFloorWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarborFloorWebApplication.class, args);
    }

	/*@Override	extends SpringBootServletInitializer
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HarborFloorWebApplication.class);
	}*/
}
