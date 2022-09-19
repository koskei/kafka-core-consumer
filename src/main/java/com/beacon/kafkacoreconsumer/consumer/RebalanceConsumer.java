package com.beacon.kafkacoreconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RebalanceConsumer {

    @KafkaListener(topicPattern = "t-balancing", concurrency = "4")
    public void getBalancingMessage(ConsumerRecord<String, String > message){
        log.info("partitions {} , Offset {} , Message {} ",
                message.partition(),
                message.offset(),
                message.value());
    }
}
