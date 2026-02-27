package org.example.crudwithkafkagradle.repository;

import org.example.crudwithkafkagradle.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}

