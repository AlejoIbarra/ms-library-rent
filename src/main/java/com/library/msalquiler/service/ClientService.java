package com.library.msalquiler.service;

import com.library.msalquiler.model.Client;
import com.library.msalquiler.model.Rent;
import com.library.msalquiler.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final HashMap<String, Object> data;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.data = new HashMap<>();
    }

    /**
     * Retrieves all clients stored in the system.
     *
     * @return List of clients.
     */
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    /**
     * Retrieves a client by ID.
     *
     * @param id ID of the client to be retrieved.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            data.put("data", client.get());
            data.put("message", "Client found successfully");
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(getErrorResponse("Client with the provided ID not found"));
        }
    }




    public List<Client> searchClients(String query) {
        return clientRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(query, query);
    }



    /**
     * Create a new client.
     *
     * @param client Client to be created.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> newClient(Client client) {
        Optional<Client> res = clientRepository.findByIdentity(client.getIdentity());

        if (res.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(getErrorResponse("Client already exists"));
        }

        clientRepository.save(client);
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
        Optional<Client> existingClient = clientRepository.findById(id);

        if (existingClient.isPresent()) {
            Client client = existingClient.get();
            updateClientProperties(client, updatedClient);

            clientRepository.save(client);
            data.put("data", client);
            data.put("message", "Client updated successfully");

            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(getErrorResponse("Client with the provided ID not found"));
        }
    }

    public ResponseEntity<Object> getClientByName(String firstName) {
        Optional<Client> client = clientRepository.findByfirstName(firstName);

        if (client.isPresent()) {
            data.put("data", client.get());
            data.put("message", "Client found successfully");
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(getErrorResponse("Client with the provided name not found"));
        }
    }





    /**
     * Deletes a client by its ID.
     *
     * @param id ID of the client to be deleted.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> deleteClient(Long id) {
        boolean exists = clientRepository.existsById(id);

        if (!exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(getErrorResponse("Client does not exist"));
        }

        clientRepository.deleteById(id);
        data.put("message", "Successfully deleted");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
    }

    public int getNumberOfRentals(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            List<Rent> rents = client.getRents();
            return rents.size();
        } else {
            return 0;
        }
    }

    public List<Rent> getRentsByClientId(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            return client.getRents();
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
        client.setFirstName(updatedClient.getFirstName());
        client.setLastName(updatedClient.getLastName());
        client.setBirthdate(updatedClient.getBirthdate());
        client.setMail(updatedClient.getMail());
        client.setAddress(updatedClient.getAddress());
        client.setPhone(updatedClient.getPhone());
        client.setIdentity(updatedClient.getIdentity());
        client.setType_identity(updatedClient.getType_identity());
    }
}
