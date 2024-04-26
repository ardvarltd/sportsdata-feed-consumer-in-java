package com.ardevar.sportsdata.feed.consumer.example.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.errors.ProductionExceptionHandler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.value;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamRecordProducerExceptionHandler implements ProductionExceptionHandler {


    @Override
    public ProductionExceptionHandlerResponse handle(ProducerRecord<byte[], byte[]> record, Exception e) {
        log.error("Exception caught during Deserialization; "
                        + "taskId: {}, topic: {}, partition: {}, offset: {}",
                value("record_topic_str", record.topic()),
                value("record_partition_str", record.partition()),
                value("exception_str", e.toString()));
        try {
            final FailedMessage failedMessage = FailedMessage.builder()
                    .customMessage("Error while deserializing")
                    .key(new String(record.key()))
                    .value(extractMessageFromString(new String(record.value())))
                    .stackTrace(Arrays.toString(e.getStackTrace()))
                    .build();
            log.error("Error encountered : {}", failedMessage.toString());
        } catch (Exception pE) {
            log.error("Exception while deserializing before going to dlq topic - {}", pE.toString());
        }
        return ProductionExceptionHandlerResponse.CONTINUE;
    }

    @Override
    public void configure(final Map<String, ?> configs) {
    }

    protected String extractMessageFromString(String record) {
        return record.substring(record.indexOf('{'));
    }

}
