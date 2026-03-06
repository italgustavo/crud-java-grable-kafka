package org.example.crudwithkafkagradle.service;

import org.example.crudwithkafkagradle.model.Sale;
import org.example.crudwithkafkagradle.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SaleServiceTest {

    private final SaleRepository saleRepository = Mockito.mock(SaleRepository.class);
    private final SaleService saleService = new SaleService(saleRepository);

    @Test
    void createSale_shouldInsertSale() {
        // Arrange
        Sale sale = new Sale("1", "client1", BigDecimal.TEN, List.of());
        when(saleRepository.insert(any(Sale.class))).thenReturn(sale);

        // Act
        Sale result = saleService.createSale(sale);

        // Assert
        assertEquals(sale, result);
        verify(saleRepository).insert(sale);
    }

    @Test
    void updateSale_shouldSaveWhenSaleExists() {
        // Arrange
        Sale sale = new Sale("1", "client1", BigDecimal.TEN, List.of());
        when(saleRepository.findById("1")).thenReturn(Optional.of(sale));
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);

        // Act
        Sale result = saleService.updateSale(sale);

        // Assert
        assertEquals(sale, result);
        verify(saleRepository).findById("1");
        verify(saleRepository).save(sale);
    }

    @Test
    void updateSale_shouldThrowWhenSaleDoesNotExist() {
        // Arrange
        Sale sale = new Sale("1", "client1", BigDecimal.TEN, List.of());
        when(saleRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> saleService.updateSale(sale));
    }

    @Test
    void getSaleById_shouldReturnSaleWhenExists() {
        // Arrange
        Sale sale = new Sale("1", "client1", BigDecimal.TEN, List.of());
        when(saleRepository.findById("1")).thenReturn(Optional.of(sale));

        // Act
        Sale result = saleService.getSaleById("1");

        // Assert
        assertEquals(sale, result);
    }

    @Test
    void getSaleById_shouldThrowWhenNotFound() {
        // Arrange
        when(saleRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> saleService.getSaleById("1"));
    }

    @Test
    void listAllSales_shouldReturnListFromRepository() {
        // Arrange
        List<Sale> sales = List.of(new Sale("1", "client1", BigDecimal.TEN, List.of()));
        when(saleRepository.findAll()).thenReturn(sales);

        // Act
        List<Sale> result = saleService.listAllSales();

        // Assert
        assertEquals(sales, result);
    }
}

