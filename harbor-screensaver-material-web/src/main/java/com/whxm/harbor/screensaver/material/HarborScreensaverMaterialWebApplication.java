package com.whxm.harbor.screensaver.material;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HarborScreensaverMaterialWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborScreensaverMaterialWebApplication.class, args);
	}

	/*@Override	extends SpringBootServletInitializer
	protected SpringApplicationBuilder configure(HarborScreensaverMaterialWebApplication builder) {
		return builder.sources(HarborFloorWebApplication.class);
	}*/
}
