package com.whxm.harbor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
