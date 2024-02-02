package com.library.msalquiler.service;

import com.library.msalquiler.model.Client;
import com.library.msalquiler.model.Rental;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

    List<Client> getClients(String name, String identity);

    ResponseEntity<Object> getClientById(Long id);

    ResponseEntity<Object> newClient(Client client);

    ResponseEntity<Object> updateClient(Long id, Client updatedClient);

    ResponseEntity<Object> deleteClient(Long id);

    int getNumberOfRentals(Long clientId);

    List<Rental> getRentalsByClientId(Long clientId);

}
