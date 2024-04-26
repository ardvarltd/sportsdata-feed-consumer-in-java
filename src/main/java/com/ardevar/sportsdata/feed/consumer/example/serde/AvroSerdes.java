package com.ardevar.sportsdata.feed.consumer.example.serde;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import models.avro.EventSportsStreamMessage;
import models.avro.MarketSportsStreamMessage;
import models.avro.SettlementSportsStreamMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "avro.serdes.enabled", havingValue = "true", matchIfMissing = false)
public class AvroSerdes {

    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    @Bean("sportStreamSettlementAvroSerde")
    public SpecificAvroSerde<SettlementSportsStreamMessage> settlementSportsStreamMessageSpecificAvroSerde() {
        final SpecificAvroSerde<SettlementSportsStreamMessage> specificAvroSerde = new SpecificAvroSerde<>();
        final Map<String, Object> map = new HashMap<>();
        map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration()).get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
        specificAvroSerde.configure(map, false);
        return specificAvroSerde;
    }

    @Bean("sportStreamEventAvroSerde")
    public SpecificAvroSerde<EventSportsStreamMessage> eventSportsStreamMessageSpecificAvroSerde() {
        final SpecificAvroSerde<EventSportsStreamMessage> specificAvroSerde = new SpecificAvroSerde<>();
        final Map<String, Object> map = new HashMap<>();
        map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration()).get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
        specificAvroSerde.configure(map, false);
        return specificAvroSerde;
    }

    @Bean("sportStreamMarketAvroSerde")
    public SpecificAvroSerde<MarketSportsStreamMessage> marketSportsStreamMessageSpecificAvroSerde() {
        final SpecificAvroSerde<MarketSportsStreamMessage> specificAvroSerde = new SpecificAvroSerde<>();
        final Map<String, Object> map = new HashMap<>();
        map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration()).get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
        specificAvroSerde.configure(map, false);
        return specificAvroSerde;
    }

}
