package org.example.crudwithkafkagradle.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.crudwithkafkagradle.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String topic;

    public ProductProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper,
            @Value("${app.kafka.topics.product:products}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.topic = topic;
    }

    public void sendProduct(Product product) {
        try {
            String payload = objectMapper.writeValueAsString(product);
            kafkaTemplate.send(topic, product.id(), payload);
            LOGGER.info("Sent product to Kafka. topic={}, key={}, payload={}", topic, product.id(), payload);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error serializing product to JSON for Kafka", e);
            throw new RuntimeException("Error sending product to Kafka", e);
        }
    }
}
