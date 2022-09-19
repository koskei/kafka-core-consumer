package com.beacon.kafkacoreconsumer.consumer;

import com.beacon.kafkacoreconsumer.entity.Images;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImageConsumer {

    final ObjectMapper objectMapper;

    public ImageConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

//    @KafkaListener(topics = "images", containerFactory = "imageRetryContainerFactory", concurrency = "2")
//    public void getMessage(final ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
//        final var images = objectMapper.readValue(consumerRecord.value(), Images.class);
//
//        if (images.getType().equalsIgnoreCase("svg")) {
//            log.error(" processing image from Partition {} : " +
//                    " \n image offset: {} \n " +
//                    " Image value {} ", consumerRecord.partition(), consumerRecord.offset(), images);
//            throw new IllegalArgumentException("Svg Message found !!!");
//        }
//        log.info(" processing image from Partition {} : " +
//                " \n image offset: {} \n " +
//                " Image value {} ", consumerRecord.partition(), consumerRecord.offset(), images);
//
//    }
}
