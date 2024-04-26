package com.ardevar.sportsdata.feed.consumer.example.handlers;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.value;

@Slf4j
@Component
public class DeserializationExceptionHandler implements org.apache.kafka.streams.errors.DeserializationExceptionHandler {

    @Override
    public DeserializationHandlerResponse handle(final ProcessorContext context,
                                                 final ConsumerRecord<byte[], byte[]> record, final Exception exception) {
        log.error("Exception caught during Deserialization; "
                        + "taskId: {}, topic: {}, partition: {}, offset: {}",
                value("context_task_str", context.taskId()),
                value("record_topic_str", record.topic()),
                value("record_partition_str", record.partition()),
                value("record_offset_str", record.offset()), exception,
                value("exception_str", exception.toString()));
        try {
            final FailedMessage failedMessage = FailedMessage.builder()
                    .customMessage("Error while deserializing")
                    .key(new String(record.key()))
                    .value(extractMessageFromString(new String(record.value())))
                    .stackTrace(Arrays.toString(exception.getStackTrace()))
                    .build();
            log.error("Error encountered : {}", failedMessage.toString());
        } catch (Exception pE) {
            log.error("Exception while deserializing before go to dlq topic - {}", pE.toString());
        }
        return DeserializationHandlerResponse.CONTINUE;
    }

    @Override
    public void configure(final Map<String, ?> configs) {
    }

    protected String extractMessageFromString(String record) {
        return record.substring(record.indexOf('{'));
    }


}
