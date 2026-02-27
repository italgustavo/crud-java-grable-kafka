package org.example.crudwithkafkagradle.model;

import org.springframework.data.annotation.Id;

public record Client(@Id String id, String name, Integer age, Endereco endereco) {
}
