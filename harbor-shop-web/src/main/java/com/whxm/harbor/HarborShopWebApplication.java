package com.whxm.harbor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HarborShopWebApplication  {

    public static void main(String[] args) {
        SpringApplication.run(HarborShopWebApplication.class, args);
    }

    //打成war包的配置
    /*@Override   extends SpringBootServletInitializer
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HarborShopWebApplication.class);
    }*/
}
