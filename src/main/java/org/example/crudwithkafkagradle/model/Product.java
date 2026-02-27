package org.example.crudwithkafkagradle.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record Product(@Id String id, String name, String description, BigDecimal value) {
}
