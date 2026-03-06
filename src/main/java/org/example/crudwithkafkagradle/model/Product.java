package org.example.crudwithkafkagradle.model;

import org.springframework.data.annotation.Id;

public record Product(@Id String id, String name, String description, Double value) {
}
