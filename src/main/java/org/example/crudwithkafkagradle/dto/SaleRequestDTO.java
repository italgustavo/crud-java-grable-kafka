package org.example.crudwithkafkagradle.dto;

import org.example.crudwithkafkagradle.model.ItemSale;

import java.util.List;

public record SaleRequestDTO(String id, String clientId, Double totalValue, List<ItemSale> items){}
