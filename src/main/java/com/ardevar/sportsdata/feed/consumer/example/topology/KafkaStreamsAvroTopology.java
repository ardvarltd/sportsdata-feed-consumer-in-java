package com.ardevar.sportsdata.feed.consumer.example.topology;

import com.ardevar.sportsdata.feed.consumer.example.config.SportsDataConfig;
import com.ardevar.sportsdata.feed.consumer.example.handlers.UncaughtExceptionHandler;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Component;
import stream.avro.sport.translations.Value;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@ConditionalOnProperty(value = "input.data.type", havingValue = "avro", matchIfMissing = false)
public class KafkaStreamsAvroTopology {

  private final SportsDataConfig sportsDataConfig;
  private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;
  private final SpecificAvroSerde<SettlementSportsStreamMessage>
      settlementSportsStreamMessageSpecificAvroSerde;
  private final SpecificAvroSerde<EventSportsStreamMessage>
      eventSportsStreamMessageSpecificAvroSerde;
  private final SpecificAvroSerde<MarketSportsStreamMessage>
      marketSportsStreamMessageSpecificAvroSerde;
  private final SpecificAvroSerde<Value> enumerationAvroSerde;
  private final SpecificAvroSerde<Country> enumCountryAvroSerde;
  private final SpecificAvroSerde<GamePeriod> enumGamePeriodAvroSerde;
  private final SpecificAvroSerde<League> enumLeagueAvroSerde;
  private final SpecificAvroSerde<LineEntity> enumLineEntityAvroSerde;
  private final SpecificAvroSerde<MarketType> enumMarketTypeAvroSerde;
  private final SpecificAvroSerde<Sport> enumSportAvroSerde;
  private final SpecificAvroSerde<Team> enumTeamSpecificAvroSerde;
  private final SpecificAvroSerde<Market> enumMarketLineSpecificAvroSerde;


  @PostConstruct
  public void defineTopology() throws Exception {

    StreamsBuilder builder = streamsBuilderFactoryBean.getObject();
    assert builder != null;

    streamsBuilderFactoryBean.setStreamsUncaughtExceptionHandler(new UncaughtExceptionHandler());

    builder.stream(
            sportsDataConfig.getComputedEventTopic(),
            Consumed.with(Serdes.String(), eventSportsStreamMessageSpecificAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Event Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getComputedMarketTopic(),
            Consumed.with(Serdes.String(), marketSportsStreamMessageSpecificAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "infoGot Record From Market Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getComputedSettlementTopic(),
            Consumed.with(Serdes.String(), settlementSportsStreamMessageSpecificAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Settlement Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getComputedEnumerationTopic(),
            Consumed.with(Serdes.String(), enumerationAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Enumerations Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getEnumCountriesTopic(),
            Consumed.with(Serdes.String(), enumCountryAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Enumerations Countries Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getEnumGamePeriodsTopic(),
            Consumed.with(Serdes.String(), enumGamePeriodAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Enumerations Game Periods Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getEnumLeaguesTopic(),
            Consumed.with(Serdes.String(), enumLeagueAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Enumerations Leagues Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getEnumLineEntitiesTopic(),
            Consumed.with(Serdes.String(), enumLineEntityAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Enumerations Line Entities Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getEnumMarketTypesTopic(),
            Consumed.with(Serdes.String(), enumMarketTypeAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Enumerations Market Type Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getEnumSportsTopic(),
            Consumed.with(Serdes.String(), enumSportAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Enumerations Sports Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getEnumTeamsTopic(),
            Consumed.with(Serdes.String(), enumTeamSpecificAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Enumerations Teams Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

    builder.stream(
            sportsDataConfig.getEnumMarketLinesTopic(),
            Consumed.with(Serdes.String(), enumMarketLineSpecificAvroSerde))
        .peek(
            (key, value) ->
                log.info(
                    "Got Record From Enumerations Market Lines Stream with id {} - {}",
                    key,
                    value != null ? value.toString() : null));

  }
}
