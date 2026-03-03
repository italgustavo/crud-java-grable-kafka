package org.example.crudwithkafkagradle.service;

import org.example.crudwithkafkagradle.model.Sale;
import org.example.crudwithkafkagradle.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale createSale(final Sale sale){
        return saleRepository.insert(sale);
    }

    public Sale updateSale(final Sale sale){
        Optional<Sale> saleOptional = saleRepository.findById(sale.id());

        if(saleOptional.isEmpty()){
            throw new RuntimeException("Sale not found " + sale.id());
        }

        return saleRepository.save(sale);
    }

    public Sale getSaleById(final String saleId){
        Optional<Sale> saleOptional = saleRepository.findById(saleId);

        return saleOptional.orElseThrow(() -> new RuntimeException("sale not found"));
    }

    public List<Sale> listAllSales(){
        return saleRepository.findAll();
    }
}
