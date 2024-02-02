package com.library.msalquiler.controller;

import com.library.msalquiler.model.Client;
import com.library.msalquiler.model.Inventory;
import com.library.msalquiler.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path ="api/v1/inventories")
@Tag(name = "Inventarios", description = "APIs para gestión de inventarios")
public class InventoryController {

    private final InventoryService service;

    @GetMapping
    @Operation(summary = "Recupera una lista de inventarios.")
    public List<Inventory> getInventory() {
        return service.getInventory();
    }

    @GetMapping("/{inventoryId}")
    @Operation(summary = "Recupera un inventario específico por ID.")
    public ResponseEntity<Object> getInventoryById(@PathVariable("inventoryId") Long id) {
        return service.getInventoryById(id);
    }

    @PostMapping
    @Operation(
            summary = "Registra un nuevo inventario.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del inventario.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventory.class))))
    public ResponseEntity<Object> newInventory(@RequestBody Inventory inventory) {
        return service.newInventory(inventory);
    }

    @PutMapping("/{inventoryId}")
    @Operation(
            summary = "Modifica todos los datos de un inventario.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del inventario a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventory.class))))
    public ResponseEntity<Object> editInventory(@PathVariable("inventoryId") Long id, @RequestBody Inventory updatedInventory) {
        return service.editInventory(id, updatedInventory);
    }
    @PatchMapping("/{inventoryId}/{newStock}")
    @Operation(summary = "Modifica el stock de un inventario específico.")
    public ResponseEntity<Object> updateStock(@PathVariable("inventoryId") Long id, @PathVariable("newStock") int newStock) {
        return service.updateStock(id, newStock);
    }

    @DeleteMapping("/{inventoryId}")
    @Operation(summary = "Elimina un inventario específico por ID.")
    public ResponseEntity<Object> deleteInventory(@PathVariable("inventoryId") Long id) {
        return service.deleteInventory(id);
    }
}
