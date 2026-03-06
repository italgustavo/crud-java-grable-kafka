package org.example.crudwithkafkagradle.service;

import org.example.crudwithkafkagradle.model.Product;
import org.example.crudwithkafkagradle.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private final ProductService productService = new ProductService(productRepository);

    @Test
    void createProduct_shouldInsertProduct() {
        // Arrange
        Product product = new Product("1", "name", "desc", 1.0);
        when(productRepository.insert(any(Product.class))).thenReturn(product);

        // Act
        Product result = productService.createProduct(product);

        // Assert
        assertEquals(product, result);
        verify(productRepository, times(1)).insert(product);
    }

    @Test
    void updateProduct_shouldSaveWhenProductExists() {
        // Arrange
        Product product = new Product("1", "name", "desc", 1.0);
        when(productRepository.findById("1")).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product result = productService.updateProduct(product);

        // Assert
        assertEquals(product, result);
        verify(productRepository).findById("1");
        verify(productRepository).save(product);
    }

    @Test
    void updateProduct_shouldThrowWhenProductDoesNotExist() {
        // Arrange
        Product product = new Product("1", "name", "desc", 1.0);
        when(productRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.updateProduct(product));
    }

    @Test
    void getProductById_shouldReturnProductWhenExists() {
        // Arrange
        Product product = new Product("1", "name", "desc", 1.0);
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        // Act
        Product result = productService.getProductById("1");

        // Assert
        assertEquals(product, result);
    }

    @Test
    void getProductById_shouldThrowWhenNotFound() {
        // Arrange
        when(productRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.getProductById("1"));
    }

    @Test
    void listAllProducts_shouldReturnListFromRepository() {
        // Arrange
        List<Product> products = List.of(new Product("1", "name", "desc", 1.0));
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.listAllProducts();

        // Assert
        assertEquals(products, result);
    }
}

