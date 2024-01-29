package com.library.msalquiler.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
/**
 * Service that manages operations related to clients.
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final HashMap<String, Object> data = new HashMap<>();

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    /**
     * Retrieves all clients stored in the system.
     *
     * @return List of clients.
     */

    public List<Client> getClients() {
        return this.clientRepository.findAll();
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
            data.put("message", "Cliente encontrado exitosamente");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            data.put("error", true);
            data.put("message", "No se encontró el cliente con el ID proporcionado");
            return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
        }
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
            data.put("error", true);
            data.put("message", "Ya está creado el cliente");
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }

        clientRepository.save(client);
        data.put("data", client);
        data.put("message", "Se creó exitosamente");

        return new ResponseEntity<>(data, HttpStatus.CREATED);
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


            client.setFirst_name(updatedClient.getFirst_name());
            client.setLast_name(updatedClient.getLast_name());
            client.setBirthdate(updatedClient.getBirthdate());
            client.setMail(updatedClient.getMail());
            client.setAddress(updatedClient.getAddress());
            client.setPhone(updatedClient.getPhone());
            client.setIdentity(updatedClient.getIdentity());
            client.setType_identity(updatedClient.getType_identity());

            clientRepository.save(client);

            data.put("data", client);
            data.put("message", "Cliente actualizado exitosamente");

            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            data.put("error", true);
            data.put("message", "No se encontró el cliente con el ID proporcionado");
            return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Deletes a client by its ID.
     *
     * @param id ID of the client to be deleted.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> deleteClient(Long id) {
        boolean exists = this.clientRepository.existsById(id);

        if (!exists) {
            data.put("error", true);
            data.put("message", "Client does not exist");
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }

        clientRepository.deleteById(id);
        data.put("message", "Eliminado Exitosamente");

        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }
}
