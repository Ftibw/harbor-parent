package com.whxm.harbor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HarborBusinessWebApplication  {

    public static void main(String[] args) {
        SpringApplication.run(HarborBusinessWebApplication.class, args);
    }

    /*@Override   extends SpringBootServletInitializer
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HarborBusinessWebApplication.class);
    }*/
}
