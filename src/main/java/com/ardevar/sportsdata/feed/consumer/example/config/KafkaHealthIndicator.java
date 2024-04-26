package com.ardevar.sportsdata.feed.consumer.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static net.logstash.logback.argument.StructuredArguments.value;

@Slf4j
@Component
public class KafkaHealthIndicator implements HealthIndicator {

    private final AdminClient kafkaAdminClient;

    private final KafkaHealthProperties kafkaHealthProperties;

    public KafkaHealthIndicator(final AdminClient kafkaAdminClient, final KafkaHealthProperties kafkaHealthProperties) {
        this.kafkaAdminClient = kafkaAdminClient;
        this.kafkaHealthProperties = kafkaHealthProperties;
    }

    @Override
    public Health getHealth(final boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        if (!isKafkaRunningProperly()) {
            return Health.down().withDetail("Kafka", "Not available").build();
        }

        return Health.up().withDetail("Kafka", "Available").build();
    }

    private Boolean isKafkaRunningProperly() {
        try {
            final ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
            Set<String> names = listTopicsResult
                    .names()
                    .get(kafkaHealthProperties.getHealthIndicator().getTimeoutMs(), TimeUnit.MILLISECONDS);
            final boolean areAllRequiredTopicsPresent = names.containsAll(kafkaHealthProperties.getTopicNames());
            if (!areAllRequiredTopicsPresent) {
                log.error("Missing Kafka topics!", value("existingKafkaTopics_str", names));
                return false;
            }

        } catch (final InterruptedException | ExecutionException | TimeoutException | NullPointerException |
                       CancellationException | java.util.concurrent.TimeoutException e) {
            log.error("Kafka is unavailable!", value("javaError", e.getMessage()));
            return false;
        }

        return true;
    }
}

