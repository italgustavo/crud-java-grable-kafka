package org.example.crudwithkafkagradle.mapper;

import org.example.crudwithkafkagradle.dto.ProductRequestDTO;
import org.example.crudwithkafkagradle.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductMapper {

    public static ProductRequestDTO toDTO(final Product product) {
        return new ProductRequestDTO(product.id(), product.name(), product.description(), BigDecimal.valueOf(product.value()));
    }

    public static List<ProductRequestDTO> toDTO(final List<Product> products) {
        return products.stream().map(ProductMapper::toDTO).toList();
    }

    public static Product toDomain(String id, ProductRequestDTO request) {
        return new Product(id, request.name(), request.description(), request.value().doubleValue());
    }
}
