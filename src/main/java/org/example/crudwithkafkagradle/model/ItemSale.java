package org.example.crudwithkafkagradle.model;

import java.math.BigDecimal;

public record ItemSale(String productId, Integer quantity, BigDecimal totalValue) {
}
