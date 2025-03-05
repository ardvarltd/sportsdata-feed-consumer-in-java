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
import sportsbook.avro.outbound.enum$.Country;
import sportsbook.avro.outbound.enum$.GamePeriod;
import sportsbook.avro.outbound.enum$.League;
import sportsbook.avro.outbound.enum$.LineEntity;
import sportsbook.avro.outbound.enum$.Market;
import sportsbook.avro.outbound.enum$.MarketType;
import sportsbook.avro.outbound.enum$.Sport;
import sportsbook.avro.outbound.enum$.Team;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import stream.avro.sport.translations.Value;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "avro.serdes.enabled", havingValue = "true", matchIfMissing = false)
public class AvroSerdes {

  private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

  @Bean("sportStreamSettlementAvroSerde")
  public SpecificAvroSerde<SettlementSportsStreamMessage> settlementSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<SettlementSportsStreamMessage> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEventAvroSerde")
  public SpecificAvroSerde<EventSportsStreamMessage> eventSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<EventSportsStreamMessage> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamMarketAvroSerde")
  public SpecificAvroSerde<MarketSportsStreamMessage> marketSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<MarketSportsStreamMessage> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEnumerationAvroSerde")
  public SpecificAvroSerde<Value> enumerationStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<Value> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEnumerationCountriesAvroSerde")
  public SpecificAvroSerde<Country> enumerationCountriesSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<Country> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEnumerationGamePeriodsAvroSerde")
  public SpecificAvroSerde<GamePeriod> enumerationGamePeriodsSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<GamePeriod> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEnumerationLeaguesAvroSerde")
  public SpecificAvroSerde<League> enumerationLeaguesSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<League> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEnumerationLineEntitiesAvroSerde")
  public SpecificAvroSerde<LineEntity> enumerationLineEntitiesSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<LineEntity> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEnumerationMarketTypesAvroSerde")
  public SpecificAvroSerde<MarketType> enumerationMarketTypesSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<MarketType> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEnumerationSportsAvroSerde")
  public SpecificAvroSerde<Sport> enumerationSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<Sport> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEnumerationTeamsAvroSerde")
  public SpecificAvroSerde<Team> enumerationTeamsSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<Team> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }

  @Bean("sportStreamEnumerationMarketLinesAvroSerde")
  public SpecificAvroSerde<Market> enumerationMarketLinesSportsStreamMessageSpecificAvroSerde() {
    final SpecificAvroSerde<Market> specificAvroSerde = new SpecificAvroSerde<>();
    final Map<String, Object> map = new HashMap<>();
    map.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        Objects.requireNonNull(streamsBuilderFactoryBean.getStreamsConfiguration())
            .get(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG));
    specificAvroSerde.configure(map, false);
    return specificAvroSerde;
  }
}
