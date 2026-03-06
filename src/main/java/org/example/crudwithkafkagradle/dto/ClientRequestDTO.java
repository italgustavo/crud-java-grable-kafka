package org.example.crudwithkafkagradle.dto;

import org.example.crudwithkafkagradle.model.Endereco;

public record ClientRequestDTO (String id, String name, Integer age, Endereco endereco){}
