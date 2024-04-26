package com.ardevar.sportsdata.feed.consumer.example.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.ContainerCustomizer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.time.Duration;

@RequiredArgsConstructor
@Configuration
public class KafkaHealthConfig<K, V> {

    private final KafkaHealthProperties kafkaHealthProperties;

    private final ConsumerFactory<String, Object> config;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(config.getConfigurationProperties());
    }


    @Bean
    public AdminClient kafkaAdminClient() {
        return AdminClient.create(kafkaAdmin().getConfigurationProperties());
    }

    @Bean
    public ContainerCustomizer<K, V, ConcurrentMessageListenerContainer<K, V>> containerCustomizer(
            ConcurrentKafkaListenerContainerFactory<K, V> noOffsetCommittingKafkaListenerContainerFactory) {

        final Duration authExceptionRetryInterval = Duration.ofMillis(
                kafkaHealthProperties.getAuthExceptionRetryIntervalMs());
        final ContainerCustomizer<K, V, ConcurrentMessageListenerContainer<K, V>> customizer = container -> container
                .getContainerProperties()
                .setAuthExceptionRetryInterval(authExceptionRetryInterval);
        noOffsetCommittingKafkaListenerContainerFactory.setContainerCustomizer(customizer);
        return customizer;
    }

}
