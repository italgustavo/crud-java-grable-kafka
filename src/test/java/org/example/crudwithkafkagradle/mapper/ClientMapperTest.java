package org.example.crudwithkafkagradle.mapper;

import org.example.crudwithkafkagradle.dto.ClientRequestDTO;
import org.example.crudwithkafkagradle.model.Client;
import org.example.crudwithkafkagradle.model.Endereco;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientMapperTest {

    @Test
    void toDTO_shouldMapClientToDto() {
        // Arrange
        Endereco endereco = new Endereco("street", "123", "city");
        Client client = new Client("1", "John", 30, endereco);

        // Act
        ClientRequestDTO dto = ClientMapper.toDTO(client);

        // Assert
        assertEquals("1", dto.id());
        assertEquals("John", dto.name());
        assertEquals(30, dto.age());
        assertEquals(endereco, dto.endereco());
    }

    @Test
    void toDTO_shouldMapClientListToDtoList() {
        // Arrange
        Endereco endereco = new Endereco("street", "123", "city");
        Client c1 = new Client("1", "John", 30, endereco);
        Client c2 = new Client("2", "Mary", 25, endereco);
        List<Client> clients = List.of(c1, c2);

        // Act
        List<ClientRequestDTO> result = ClientMapper.toDTO(clients);

        // Assert
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).id());
        assertEquals("2", result.get(1).id());
    }

    @Test
    void toModel_shouldMapDtoToClient() {
        // Arrange
        Endereco endereco = new Endereco("street", "123", "city");
        ClientRequestDTO dto = new ClientRequestDTO("1", "John", 30, endereco);

        // Act
        Client client = ClientMapper.toModel(dto);

        // Assert
        assertEquals("1", client.id());
        assertEquals("John", client.name());
        assertEquals(30, client.age());
        assertEquals(endereco, client.endereco());
    }
}

