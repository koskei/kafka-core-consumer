package com.beacon.kafkacoreconsumer.consumer;


import com.beacon.kafkacoreconsumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeConsumer {

    private final ObjectMapper objectMapper;

    public EmployeeConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

//    @KafkaListener(topics = "employeev1")
//    public void consumeKafkaMessage (final String message) throws JsonProcessingException, IllegalAccessException {
//        var employee = objectMapper.readValue(message, Employee.class);
//        if (Integer.valueOf(employee.getEmployeeId()) % 2 ==1) {
//            throw new IllegalAccessException("number passed is odd");
//        }
//        log.info("Consuming message from kafka consumer {} ", employee);
//    }
}