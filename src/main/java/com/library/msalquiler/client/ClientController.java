package com.library.msalquiler.client;

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
    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @GetMapping
    public List<Client> getClients(){
        return this.clientService.getClients();
    }

    @GetMapping(path = "{clientId}")
    public ResponseEntity<Object> getClientById(@PathVariable("clientId") Long id){
        return this.clientService.getClientById(id);
    }

    @PostMapping
    public ResponseEntity<Object> registerClient(@RequestBody Client client) {
        return this.clientService.newClient(client);
    }

    //Falta terminar el Actualizar
    //@PutMapping
    //public ResponseEntity<Object> updateClient(@RequestBody Client client) {
    //    return this.clientService.newClient(client);
    //}
    @PutMapping(path = "{clientId}")
    public ResponseEntity<Object> updateClient(@PathVariable("clientId") Long id, @RequestBody Client updatedClient){
        return this.clientService.updateClient(id, updatedClient);
    }


    @DeleteMapping(path = "productId")
    public ResponseEntity<Object> delete(@PathVariable("productId") Long id){
        return this.clientService.deleteClient(id);
    }
}
