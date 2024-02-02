package com.library.msalquiler.controller;

import com.library.msalquiler.model.Rental;
import com.library.msalquiler.service.ClientService;
import com.library.msalquiler.model.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path ="api/v1/clients")
@Tag(name = "Clientes", description = "APIs para gestión de clientes")
public class ClientController {

    private final ClientService service;

    @GetMapping
    @Operation(summary = "Recupera una lista de clientes.")
    public List<Client> getClients(
            @Parameter(name = "name", description = "Nombre del cliente.", example = "Ana")
            @RequestParam(required = false) String name,
            @Parameter(name = "identity", description = "Documento de identidad del cliente.", example = "1122334455667")
            @RequestParam(required = false) String identity) {
        return this.service.getClients(name, identity);
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Recupera un cliente específico por ID.")
    public ResponseEntity<Object> getClientById(@PathVariable("clientId") Long id) {
        return this.service.getClientById(id);
    }

    @PostMapping
    @Operation(
            summary = "Registra un nuevo cliente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del cliente.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))))
    public ResponseEntity<Object> registerClient(@RequestBody Client client) {
        return this.service.newClient(client);
    }

    @PutMapping("/{clientId}")
    @Operation(
            summary = "Modifica todos los datos de un cliente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del cliente a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))))
    public ResponseEntity<Object> updateClient(@PathVariable("clientId") Long id, @RequestBody Client updatedClient) {
        return this.service.updateClient(id, updatedClient);
    }

    @GetMapping("/{clientId}/rentals")
    @Operation(summary = "Recupera los aquileres de un cliente específico por ID.")
    public ResponseEntity<Object> getRentalsByClientId(@PathVariable("clientId") Long clientId) {
            List<Rental> rentals = service.getRentalsByClientId(clientId);
            return ResponseEntity.ok(rentals);

    }

    @DeleteMapping("/{clientId}")
    @Operation(summary = "Elimina un cliente específico por ID.")
    public ResponseEntity<Object> delete(@PathVariable("clientId") Long clientId){
        return this.service.deleteClient(clientId);
    }
}
