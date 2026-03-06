package org.example.crudwithkafkagradle.service;

import org.example.crudwithkafkagradle.model.Client;
import org.example.crudwithkafkagradle.model.Endereco;
import org.example.crudwithkafkagradle.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private final ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
    private final ClientService clientService = new ClientService(clientRepository);

    @Test
    void createCliente_shouldInsertClient() {
        // Arrange
        Endereco endereco = new Endereco("street", "123", "city");
        Client client = new Client("1", "John", 30, endereco);
        when(clientRepository.insert(any(Client.class))).thenReturn(client);

        // Act
        Client result = clientService.createCliente(client);

        // Assert
        assertEquals(client, result);
        verify(clientRepository).insert(client);
    }

    @Test
    void updateClient_shouldSaveWhenClientExists() throws Exception {
        // Arrange
        Endereco endereco = new Endereco("street", "123", "city");
        Client client = new Client("1", "John", 30, endereco);
        when(clientRepository.findById("1")).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        // Act
        Client result = clientService.updateClient(client);

        // Assert
        assertEquals(client, result);
        verify(clientRepository).findById("1");
        verify(clientRepository).save(client);
    }

    @Test
    void updateClient_shouldThrowWhenClientDoesNotExist() {
        // Arrange
        Endereco endereco = new Endereco("street", "123", "city");
        Client client = new Client("1", "John", 30, endereco);
        when(clientRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> clientService.updateClient(client));
    }

    @Test
    void getClientById_shouldReturnClientWhenExists() throws Exception {
        // Arrange
        Endereco endereco = new Endereco("street", "123", "city");
        Client client = new Client("1", "John", 30, endereco);
        when(clientRepository.findById("1")).thenReturn(Optional.of(client));

        // Act
        Client result = clientService.getClientById("1");

        // Assert
        assertEquals(client, result);
    }

    @Test
    void getClientById_shouldThrowWhenNotFound() {
        // Arrange
        when(clientRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> clientService.getClientById("1"));
    }

    @Test
    void listAll_shouldReturnListFromRepository() {
        // Arrange
        Endereco endereco = new Endereco("street", "123", "city");
        List<Client> clients = List.of(new Client("1", "John", 30, endereco));
        when(clientRepository.findAll()).thenReturn(clients);

        // Act
        List<Client> result = clientService.listAll();

        // Assert
        assertEquals(clients, result);
    }
}

