package org.example.crudwithkafkagradle.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.crudwithkafkagradle.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductProducerTest {

    private final KafkaTemplate<String, String> kafkaTemplate = mock(KafkaTemplate.class);
    private final ObjectMapper objectMapper = mock(ObjectMapper.class);

    @Test
    void sendProduct_shouldSendSerializedProductToKafka() throws Exception {
        // Arrange
        Product product = new Product("1", "name", "desc", 1.0);
        when(objectMapper.writeValueAsString(product)).thenReturn("{\"id\":\"1\"}");
        ProductProducer producer = new ProductProducer(kafkaTemplate, objectMapper, "products");

        // Act
        producer.sendProduct(product);

        // Assert
        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> payloadCaptor = ArgumentCaptor.forClass(String.class);

        verify(kafkaTemplate).send(topicCaptor.capture(), keyCaptor.capture(), payloadCaptor.capture());

        assertEquals("products", topicCaptor.getValue());
        assertEquals("1", keyCaptor.getValue());
        assertEquals("{\"id\":\"1\"}", payloadCaptor.getValue());
    }

    @Test
    void sendProduct_shouldThrowRuntimeExceptionWhenSerializationFails() throws Exception {
        // Arrange
        Product product = new Product("1", "name", "desc", 1.0);
        when(objectMapper.writeValueAsString(any())).thenThrow(new JsonProcessingException("error") {});
        ProductProducer producer = new ProductProducer(kafkaTemplate, objectMapper, "products");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> producer.sendProduct(product));
    }
}

