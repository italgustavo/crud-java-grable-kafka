package org.example.crudwithkafkagradle.mapper;

import org.example.crudwithkafkagradle.dto.ClientRequestDTO;
import org.example.crudwithkafkagradle.dto.ProductRequestDTO;
import org.example.crudwithkafkagradle.model.Product;

import java.util.List;

public class ProductMapper {

    public static ProductRequestDTO toDTO(final Product product){
        return new ProductRequestDTO(product.id(),product.name(), product.description(), product.value());
    }

    public static List<ProductRequestDTO> toDTO(final List<ProductRequestDTO> product){
        return product.stream().map(ProductMapper::toDTO).toList();
    }

    public static Product toDomain(String id, ProductRequestDTO request) {
        return new Product(id, request.name(), request.description(), request.value());
    }
}
