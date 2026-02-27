package org.example.crudwithkafkagradle.repository;

import org.example.crudwithkafkagradle.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}
