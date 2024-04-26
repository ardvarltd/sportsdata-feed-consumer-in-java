package com.ardevar.sportsdata.feed.consumer.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sportsdata")
public class SportsDataConfig {

    private String computedSettlementTopic;

    private String computedMarketTopic;

    private String computedEventTopic;

}
