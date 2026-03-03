package org.example.crudwithkafkagradle.controller;

import org.example.crudwithkafkagradle.model.Client;
import org.example.crudwithkafkagradle.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> listAll(){
        return ResponseEntity.ok(clientService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable final String id) throws Exception {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(final Client saleRequest) throws Exception {
        final Client client = clientService.updateClient(saleRequest);

        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody final Client clientRequest){

        if(clientRequest != null){
            return ResponseEntity.ok(clientService.createCliente(clientRequest));
        }

        return ResponseEntity.notFound().build();
    }
}
