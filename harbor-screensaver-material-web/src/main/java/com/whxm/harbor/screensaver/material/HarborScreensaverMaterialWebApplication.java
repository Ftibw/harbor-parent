package com.whxm.harbor.screensaver.material;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HarborScreensaverMaterialWebApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HarborScreensaverMaterialWebApplication.class, args);
	}

	//打成war包的配置
    /*@Override   extends SpringBootServletInitializer
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HarborScreensaverMaterialWebApplication.class);
    }*/
}
