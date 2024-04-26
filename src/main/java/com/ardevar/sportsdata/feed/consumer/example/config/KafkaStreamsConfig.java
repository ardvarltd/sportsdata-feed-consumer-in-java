package com.ardevar.sportsdata.feed.consumer.example.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class KafkaStreamsConfig {

    private final ConsumerFactory<String, Object> consumerFactory;


    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration buildStreamsConfig() {
        Map<String, Object> props = new HashMap<>(consumerFactory.getConfigurationProperties());
        return new KafkaStreamsConfiguration(props);
    }
}