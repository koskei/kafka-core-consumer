package com.beacon.kafkacoreconsumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class kafkaConfig {
    private final KafkaProperties kafkaProperties;

    public kafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        final var properties = kafkaProperties.buildConsumerProperties();
        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG,"120000");
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());
        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(10_000, 3)));
        return factory;
    }


    @Bean(name = "InvoiceDlqFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> InvoiceDlqFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            KafkaTemplate<String, String> kafkaTemplate) {

        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());

        final var invoiceDlq = new DeadLetterPublishingRecoverer(kafkaTemplate,
                (consumerRecord, e) -> new TopicPartition("invoice", consumerRecord.partition()));

        factory.setCommonErrorHandler(new DefaultErrorHandler(invoiceDlq, new FixedBackOff(10_000, 3)));
        return factory;
    }



}
