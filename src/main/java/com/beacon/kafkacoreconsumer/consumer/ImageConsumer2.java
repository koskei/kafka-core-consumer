package com.beacon.kafkacoreconsumer.consumer;

import com.beacon.kafkacoreconsumer.entity.Images2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImageConsumer2 {

    final ObjectMapper objectMapper;

    public ImageConsumer2(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RetryableTopic(autoCreateTopics = "true"
            , attempts = "4",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            backoff = @Backoff(delay = 3000, maxDelay = 10_000, multiplier = 1.5, random = true),
            dltTopicSuffix = "-dead"
    )
    @KafkaListener(topics = "images2", concurrency = "2")
    public void getMessage(final ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        final var images = objectMapper.readValue(consumerRecord.value(), Images2.class);

        if (images.getType().equalsIgnoreCase("svg")) {
            log.error(" processing image from Partition {} : " +
                    " \n image offset: {} \n " +
                    " Image value {} ", consumerRecord.partition(), consumerRecord.offset(), images);
            throw new IllegalArgumentException("Svg Message found !!!");
        }
        log.info(" processing image from Partition {} : " +
                " \n image offset: {} \n " +
                " Image value {} ", consumerRecord.partition(), consumerRecord.offset(), images);

    }
}
