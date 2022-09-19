package com.beacon.kafkacoreconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class KafkaKeyConsumer {

//    @KafkaListener(topics = "newPartition", concurrency = "3")
//    public void consumeMessage(ConsumerRecord<String, String> consumerRecord)  {
//        log.info( "Key {} partition {} message {} ", consumerRecord.key(), consumerRecord.partition(), consumerRecord.value());
////        TimeUnit.SECONDS.sleep(1);
//    }
}
