package com.library.msalquiler.controller;

import com.library.msalquiler.model.Rental;
import com.library.msalquiler.model.request.RentalRequest;
import com.library.msalquiler.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1")
@Tag(name = "Alquileres", description = "APIs para gestión de alquileres")
public class RentalController {

    private final RentalService service;

    @GetMapping("/rentals")
    @Operation(summary = "Recupera una lista de alquileres.")
    public List<Rental> getRentals(
            @Parameter(name = "start", description = "Fecha de inicio de alquiler.", example = "2024-01-01")
            @RequestParam(required = false) LocalDate startDate) {
        return service.getRentals(startDate);
    }

    @GetMapping("/rentals/{rentalId}")
    @Operation(summary = "Recupera un alquiler específico por ID.")
    public ResponseEntity<Rental> getRental(@PathVariable Long rentalId) {
        Rental rental = service.getRentalById(rentalId);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
    }

    @PostMapping("/clients/{clientId}/rentals")
    @Operation(
            summary = "Registra un nuevo alquiler de un cliente específico.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del alquiler.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalRequest.class))))
    public ResponseEntity<Object> registerRental(@PathVariable Long clientId, @Valid @RequestBody RentalRequest request) {
        return service.newRental(clientId, request);
    }

    @PutMapping("/rentals/{rentalId}")
    @Operation(
            summary = "Modifica todos los datos de un alquiler.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del alquiler a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rental.class))))
    public ResponseEntity<Object> updateRental(@PathVariable Long rentalId, @RequestBody Rental updatedRental) {
        return service.updateRental(rentalId, updatedRental);
    }

    @PatchMapping("/rentals/{rentalId}")
    @Operation(summary = "Modifica la fecha de devolución de un alquiler específico por ID.")
    public ResponseEntity<Object> patchReturnDate(@PathVariable Long rentalId) {
        return service.patchReturnDate(rentalId);
    }

    @DeleteMapping("/rentals/{rentalId}")
    @Operation(summary = "Elimina un alquiler específico por ID.")
    public ResponseEntity<Object> delete(@PathVariable Long rentalId){
        return service.deleteRental(rentalId);
    }
}