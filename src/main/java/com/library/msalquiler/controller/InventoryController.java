package com.library.msalquiler.controller;

import com.library.msalquiler.model.Inventory;
import com.library.msalquiler.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();
    }

    @GetMapping(path = "{inventoryId}")
    public ResponseEntity<Object> getInventoryById(@PathVariable("inventoryId") Long id) {
        return inventoryService.getInventoryById(id);
    }

    @PostMapping
    public ResponseEntity<Object> newInventory(@RequestBody Inventory inventory) {
        return inventoryService.newInventory(inventory);
    }

    @PutMapping(path = "{inventoryId}")
    public ResponseEntity<Object> editInventory(@PathVariable("inventoryId") Long id, @RequestBody Inventory updatedInventory) {
        return inventoryService.editInventory(id, updatedInventory);
    }
    @PatchMapping(path = "{inventoryId}/update-stock")
    public ResponseEntity<Object> updateStock(@PathVariable("inventoryId") Long id, @RequestParam("stock") int newStock) {
        return inventoryService.updateStock(id, newStock);
    }

    @DeleteMapping(path = "{inventoryId}")
    public ResponseEntity<Object> deleteInventory(@PathVariable("inventoryId") Long id) {
        return inventoryService.deleteInventory(id);
    }
}
