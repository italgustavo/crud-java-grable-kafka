package org.example.crudwithkafkagradle.mapper;

import org.example.crudwithkafkagradle.dto.ClientRequestDTO;
import org.example.crudwithkafkagradle.model.Client;

import java.util.List;

public class ClientMapper {

    public static ClientRequestDTO toDTO(final Client client){
        return new ClientRequestDTO(client.id(), client.name(), client.age(), client.endereco());
    }

    public static List<ClientRequestDTO> toDTO(List<Client> clients){
        return clients.stream().map(ClientMapper::toDTO).toList();
    }

    public static Client toModel(final ClientRequestDTO request){
        return new Client(request.id(), request.name(), request.age(), request.endereco());
    }

}
