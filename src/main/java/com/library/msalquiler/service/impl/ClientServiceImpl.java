package com.library.msalquiler.service.impl;

import com.library.msalquiler.model.Client;
import com.library.msalquiler.model.Rental;
import com.library.msalquiler.repository.ClientRepository;
import com.library.msalquiler.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final HashMap<String, Object> data;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
        this.data = new HashMap<>();
    }

    /**
     * Retrieves all clients stored in the system.
     *
     * @return List of clients.
     */
    public List<Client> getClients(String name, String identity) {
        if (StringUtils.hasLength(name) || StringUtils.hasLength(identity)) {
            return repository.search(name, identity);
        }
        List<Client> clients = repository.findAll();
        return clients.isEmpty() ? null : clients;
    }

    /**
     * Retrieves a client by ID.
     *
     * @param id ID of the client to be retrieved.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> getClientById(Long id) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) {
            data.put("data", client.get());
            data.put("message", "Client found successfully");
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(getErrorResponse("Client with the provided ID not found"));
        }
    }

    /**
     * Create a new client.
     *
     * @param client Client to be created.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> newClient(Client client) {
        Optional<Client> res = repository.findByIdentity(client.getIdentity());

        if (res.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(getErrorResponse("Client already exists"));
        }

        repository.save(client);
        data.put("data", client);
        data.put("message", "Successfully created");

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    /**
     * Updates the information of an existing client.
     *
     * @param id            ID of the client to be updated.
     * @param updatedClient Client with the updated information.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> updateClient(Long id, Client updatedClient) {
       Optional<Client> existingClient = repository.findById(id);

        if (existingClient.isPresent()) {
            updateClientProperties(existingClient.get(), updatedClient);
            repository.save(existingClient.get());
            data.put("data", existingClient.get());
            data.put("message", "Client updated successfully");
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(getErrorResponse("Client with the provided ID not found"));
        }
    }

    /**
     * Deletes a client by its ID.
     *
     * @param id ID of the client to be deleted.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> deleteClient(Long id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(getErrorResponse("Client does not exist"));
        }

        repository.deleteById(id);
        data.put("message", "Successfully deleted");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
    }

    public int getNumberOfRentals(Long clientId) {
        Optional<Client> clientOptional = repository.findById(clientId);

        if (clientOptional.isPresent()) {
            List<Rental> rentals = clientOptional.get().getRentals();
            return rentals.size();
        } else {
            return 0;
        }
    }

    public List<Rental> getRentalsByClientId(Long clientId) {
        Optional<Client> client = repository.findById(clientId);

        if (client.isPresent()) {
            return client.get().getRentals();
        } else {
            throw new EntityNotFoundException("Client with id " + clientId + " not found");
        }
    }

    private HashMap<String, Object> getErrorResponse(String errorMessage) {
        data.put("error", true);
        data.put("message", errorMessage);
        return data;
    }

    private void updateClientProperties(Client client, Client updatedClient) {
        client.setName(updatedClient.getName());
        client.setBirthday(updatedClient.getBirthday());
        client.setEmail(updatedClient.getEmail());
        client.setAddress(updatedClient.getAddress());
        client.setPhone(updatedClient.getPhone());
        client.setIdentity(updatedClient.getIdentity());
        client.setIdentityType(updatedClient.getIdentityType());
    }
}
