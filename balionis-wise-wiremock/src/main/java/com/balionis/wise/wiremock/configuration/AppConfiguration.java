package com.balionis.wise.wiremock.configuration;

import com.balionis.wise.wiremock.util.AppLoggingNotifier;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.Notifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfiguration {

    private final AppConfigurationProperties appConfigurationProperties;

    private WireMockServer wireMockServer;

    @PostConstruct
    public void start() {
        log.info("starting...");
        wireMockServer = buildWireMockServer(buildWireMockNotifier());
        wireMockServer.start();
    }

    @PreDestroy
    public void shutdown() {
        log.info("shutdown...");
        wireMockServer.shutdown();
    }

    public WireMockServer buildWireMockServer(Notifier notifier) {
        var wireMockConfiguration = WireMockConfiguration.options()
                .notifier(notifier)
                .port(appConfigurationProperties.getPort())
                .usingFilesUnderClasspath("BOOT-INF/classes/wiremock");
        return new WireMockServer(wireMockConfiguration);
    }

    public static Notifier buildWireMockNotifier() {
        return new AppLoggingNotifier();
    }
}
