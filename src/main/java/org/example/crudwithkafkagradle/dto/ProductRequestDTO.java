package org.example.crudwithkafkagradle.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, Double value){
    public ProductRequestDTO(String id, String name, String description, BigDecimal value) {
    }

    public ProductRequestDTO(String id, String name, String description, Double value) {
    }
}
