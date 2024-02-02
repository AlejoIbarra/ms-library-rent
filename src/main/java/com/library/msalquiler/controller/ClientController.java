package com.library.msalquiler.controller;

import com.library.msalquiler.model.Rent;
import com.library.msalquiler.service.ClientService;
import com.library.msalquiler.model.Client;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling client operations.
 */
@RestController
@RequestMapping(path = "api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    /**
     * Constructor to initialize ClientController with ClientService.
     *
     * @param clientService The service responsible for client operations.
     */
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Get the list of all clients.
     *
     * @return List of Client objects representing all clients.
     */
    @GetMapping
    public List<Client> getClients() {
        return this.clientService.getClients();
    }

    /**
     * Get a client by their ID.
     *
     * @param id The ID of the client to retrieve.
     * @return ResponseEntity containing the client object, or an error message if not found.
     */
    @GetMapping(path = "{clientId}")
    public ResponseEntity<Object> getClientById(@PathVariable("clientId") Long id) {
        return this.clientService.getClientById(id);
    }

    /**
     * Register a new client.
     *
     * @param client The Client object representing the new client.
     * @return ResponseEntity with the result of the client registration.
     */
    @PostMapping
    public ResponseEntity<Object> registerClient(@RequestBody Client client) {
        return this.clientService.newClient(client);
    }

    /**
     * Update an existing client.
     *
     * @param id           The ID of the client to update.
     * @param updatedClient The updated Client object.
     * @return ResponseEntity with the result of the client update.
     */
    @PutMapping(path = "{clientId}")
    public ResponseEntity<Object> updateClient(@PathVariable("clientId") Long id, @RequestBody Client updatedClient) {
        return this.clientService.updateClient(id, updatedClient);
    }

    /**
     * Get the number of rentals for a client.
     *
     * @param clientId The ID of the client.
     * @return ResponseEntity with the number of rentals for the client.
     */
    @GetMapping(path = "{clientId}/rents-count")
    public ResponseEntity<Object> getRentCountForClient(@PathVariable("clientId") Long clientId) {
        int numberOfRentals = clientService.getNumberOfRentals(clientId);
        return ResponseEntity.ok("Number of rentals for client " + clientId + ": " + numberOfRentals);
    }

    /**
     * Get a client by their first name.
     *
     * @param firstName The first name of the client to search for.
     * @return ResponseEntity containing the client object, or an error message if not found.
     */
    @GetMapping("/byname/{first_name}")
    public ResponseEntity<Object> getClientByName(@PathVariable String firstName) {
        return clientService.getClientByName(firstName);
    }

    /**
     * Search for clients by name.
     *
     * @param query The query string to search for.
     * @return ResponseEntity containing the list of clients matching the search query.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchClients(@RequestParam(value = "query") String query) {
        List<Client> clients = clientService.searchClients(query);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Get the list of rents for a client.
     *
     * @param clientId The ID of the client.
     * @return ResponseEntity containing the list of rents for the client.
     */
    @GetMapping(path = "{clientId}/rents")
    public ResponseEntity<Object> getRentsByClientId(@PathVariable("clientId") Long clientId) {
        List<Rent> rents = clientService.getRentsByClientId(clientId);
        return ResponseEntity.ok(rents);
    }

    /**
     * Delete a client by ID.
     *
     * @param id The ID of the client to delete.
     * @return ResponseEntity with the result of the client deletion.
     */
    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> delete(@PathVariable("productId") Long id){
        return this.clientService.deleteClient(id);
    }
}
