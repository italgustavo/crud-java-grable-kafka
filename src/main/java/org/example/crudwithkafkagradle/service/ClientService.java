package org.example.crudwithkafkagradle.service;

import org.example.crudwithkafkagradle.model.Client;
import org.example.crudwithkafkagradle.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

        private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

        private  ClientRepository clientRepository = null;


        public ClientService(final ClientRepository clientRepository){
                this.clientRepository = clientRepository;

        }

        public Client createCliente(final Client client){
                return clientRepository.insert(client);
        }

        public Client updateClient(final Client client) throws Exception {
                final Optional<Client> clientOptional = clientRepository.findById(client.id());

                if(clientOptional.isPresent()) {
                        throw new Exception("Client not exists " + client );
                }

                return clientRepository.save(client);
        }

        public Client getClientById(final String clientId) throws Exception {
                final Optional<Client> clientOptional = clientRepository.findById(clientId);

                return clientOptional.orElseThrow(() ->
                        new Exception("Client not found"));
        }

        public List<Client> listAll() {
            return clientRepository.findAll();
        }
}
