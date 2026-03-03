package org.example.crudwithkafkagradle.service;

import org.example.crudwithkafkagradle.model.Product;
import org.example.crudwithkafkagradle.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;


    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(final Product product) {
        return productRepository.insert(product);
    }

    public Product updateProduct(final Product product){
        final Optional<Product> productOptional = productRepository.findById(product.id());

            if(productOptional.isEmpty()){
                throw new RuntimeException("Product not found" + product.id());
            }

            return productRepository.save(product);
    }

    public Product getProductById(final String productId){
        final Optional<Product> productOptional = productRepository.findById(productId);

        return productOptional.orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }
}
