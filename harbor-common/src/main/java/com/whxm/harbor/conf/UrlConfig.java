package com.whxm.harbor.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlConfig {
    @Value("${urlPrefix}")
    private String urlPrefix;

    public String getUrlPrefix() {
        return urlPrefix;
    }
}
