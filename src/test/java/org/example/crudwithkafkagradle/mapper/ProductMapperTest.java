package org.example.crudwithkafkagradle.mapper;

import org.example.crudwithkafkagradle.dto.ProductRequestDTO;
import org.example.crudwithkafkagradle.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {

    @Test
    void toDTO_shouldMapProductToDto() {
        // Arrange
        Product product = new Product("1", "name", "desc", 10.5);

        // Act
        ProductRequestDTO dto = ProductMapper.toDTO(product);

        // Assert
        assertEquals("1", dto.id());
        assertEquals("name", dto.name());
        assertEquals("desc", dto.description());
        assertEquals(BigDecimal.valueOf(10.5), dto.value());
    }

    @Test
    void toDTO_shouldMapProductListToDtoList() {
        // Arrange
        Product p1 = new Product("1", "p1", "d1", 5.0);
        Product p2 = new Product("2", "p2", "d2", 7.0);
        List<Product> products = List.of(p1, p2);

        // Act
        List<ProductRequestDTO> result = ProductMapper.toDTO(products);

        // Assert
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).id());
        assertEquals("2", result.get(1).id());
    }

    @Test
    void toDomain_shouldMapDtoToProduct() {
        // Arrange
        ProductRequestDTO dto = new ProductRequestDTO("1", "name", "desc", BigDecimal.valueOf(3.3));

        // Act
        Product product = ProductMapper.toDomain("1", dto);

        // Assert
        assertEquals("1", product.id());
        assertEquals("name", product.name());
        assertEquals("desc", product.description());
        assertEquals(3.3, product.value());
    }
}

