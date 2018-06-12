package com.whxm.harbor.screensaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HarborScreensaverWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborScreensaverWebApplication.class, args);
	}

	//打成war包的配置
    /*@Override   extends SpringBootServletInitializer
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HarborScreensaverWebApplication.class);
    }*/
}
