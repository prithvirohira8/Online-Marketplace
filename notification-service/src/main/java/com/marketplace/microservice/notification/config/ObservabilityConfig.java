package com.marketplace.microservice.notification.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ObservabilityConfig {

    private final ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;

    public ObservabilityConfig(ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory) {
        this.concurrentKafkaListenerContainerFactory = concurrentKafkaListenerContainerFactory;
    }

    @PostConstruct
    public void setObservationForKafkaTemplate() {
        concurrentKafkaListenerContainerFactory.getContainerProperties().setObservationEnabled(true);
    }

    @Bean
    ObservedAspect observedAspect(ObservationRegistry registry) {
        return new ObservedAspect(registry);
    }
}
