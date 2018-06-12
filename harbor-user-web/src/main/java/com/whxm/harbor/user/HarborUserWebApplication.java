package com.whxm.harbor.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.whxm.harbor.common.interceptor")
public class HarborUserWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborUserWebApplication.class, args);
	}

	//打成war包的配置
   /* @Override   extends SpringBootServletInitializer
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HarborUserWebApplication.class);
    }*/
}
