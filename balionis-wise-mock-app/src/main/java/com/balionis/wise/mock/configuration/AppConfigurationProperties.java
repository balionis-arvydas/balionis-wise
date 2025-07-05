package com.balionis.wise.mock.configuration;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.service")
@Slf4j
@Data
public class AppConfigurationProperties {
    private String name;

    private WiremockProperties wiremock = new WiremockProperties();

    @PostConstruct
    public void logPostConstruct() {
        log.info("appConfigurationProperties={}", this);
    }

    @Data
    class WiremockProperties {
        private Integer port;
    }
}
