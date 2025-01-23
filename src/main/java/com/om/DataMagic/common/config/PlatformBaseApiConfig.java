package com.om.DataMagic.common.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "base-api")
@Setter
@Getter
public class PlatformBaseApiConfig {

    private String gitcode;
    private String gitee;
    private String github;


}
