package com.ardevar.sportsdata.feed.consumer.example.handlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class FailedMessage {
    private final String customMessage;

    private final String key;

    private final Object value;

    private final String stackTrace;

}
