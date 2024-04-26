package com.ardevar.sportsdata.feed.consumer.example.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("kafka.health")
public class KafkaHealthProperties {

    private HealthIndicatorConfig healthIndicator;

    private List<String> topicNames;

    private Long authExceptionRetryIntervalMs;

    @Getter
    @Setter
    public static class HealthIndicatorConfig {

        private int timeoutMs;

    }
}
