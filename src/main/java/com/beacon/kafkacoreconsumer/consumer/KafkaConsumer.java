package com.beacon.kafkacoreconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

//    @KafkaListener(topics = "-t-hello")
//    public void consumeKafkaMessage (final String message) {
//        log.info("Consuming message from kafka consumer {} ", message);
//    }
}
