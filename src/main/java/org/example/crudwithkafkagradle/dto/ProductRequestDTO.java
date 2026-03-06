package org.example.crudwithkafkagradle.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(String id, String name, String description, BigDecimal value) {
}
