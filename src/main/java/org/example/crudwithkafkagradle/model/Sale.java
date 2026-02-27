package org.example.crudwithkafkagradle.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record Sale(@Id String id, String clientId, BigDecimal totalValue, List<ItemSale> items) {
}
