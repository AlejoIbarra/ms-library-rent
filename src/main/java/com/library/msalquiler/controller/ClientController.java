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

@RestController
@RequestMapping(path ="api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients() {
        return this.clientService.getClients();
    }

    @GetMapping(path = "{clientId}")
    public ResponseEntity<Object> getClientById(@PathVariable("clientId") Long id) {
        return this.clientService.getClientById(id);
    }

    @PostMapping
    public ResponseEntity<Object> registerClient(@RequestBody Client client) {
        return this.clientService.newClient(client);
    }


    @PutMapping(path = "{clientId}")
    public ResponseEntity<Object> updateClient(@PathVariable("clientId") Long id, @RequestBody Client updatedClient) {
        return this.clientService.updateClient(id, updatedClient);
    }

    @GetMapping(path = "{clientId}/rents-count")
    public ResponseEntity<Object> getRentCountForClient(@PathVariable("clientId") Long clientId) {
        int numberOfRentals = clientService.getNumberOfRentals(clientId);
        return ResponseEntity.ok("Number of rentals for client " + clientId + ": " + numberOfRentals);
    }



    @GetMapping("/byname/{first_name}")
    public ResponseEntity<Object> getClientByName(@PathVariable String firstName) {
        return clientService.getClientByName(firstName);
    }


    //http://localhost:8080/api/v1/clients/search?query=Ale
    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchClients(@RequestParam(value = "query") String query) {
        List<Client> clients = clientService.searchClients(query);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    @GetMapping(path = "{clientId}/rents")
    public ResponseEntity<Object> getRentsByClientId(@PathVariable("clientId") Long clientId) {

            List<Rent> rents = clientService.getRentsByClientId(clientId);
            return ResponseEntity.ok(rents);

    }

    @DeleteMapping(path = "productId")
    public ResponseEntity<Object> delete(@PathVariable("productId") Long id){
        return this.clientService.deleteClient(id);
    }
}
