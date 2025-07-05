package com.balionis.wise.wiremock.configuration;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wiremock")
@Slf4j
@Data
public class AppConfigurationProperties {

    private Integer port;

    @PostConstruct
    public void logPostConstruct() {
        log.info("wireMockProperties={}", this);
    }
}
