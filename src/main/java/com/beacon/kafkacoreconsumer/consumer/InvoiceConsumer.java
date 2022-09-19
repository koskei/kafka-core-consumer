package com.beacon.kafkacoreconsumer.consumer;

import com.beacon.kafkacoreconsumer.entity.Images;
import com.beacon.kafkacoreconsumer.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceConsumer {

    final ObjectMapper objectMapper;

    public InvoiceConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

//    @KafkaListener(topics = "invoice", containerFactory = "InvoiceDlqFactory", concurrency = "2")
//    public void consumeMessage(final ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
//        final var invoice = objectMapper.readValue(consumerRecord.value(), Invoice.class);
//
//        if (invoice.getAmount() < 1) {
//            log.error(" processing image from Partition {} : " +
//                    " \n image offset: {} \n " +
//                    " Image value {} ", consumerRecord.partition(), consumerRecord.offset(), invoice);
//            throw new IllegalArgumentException("Svg Message found !!!");
//        }
//        log.info(" processing image from Partition {} : " +
//                " \n image offset: {} \n " +
//                " Image value {} ", consumerRecord.partition(), consumerRecord.offset(), invoice);
//
//    }
}
