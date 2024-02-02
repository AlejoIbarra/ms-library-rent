package com.library.msalquiler.controller;

import com.library.msalquiler.model.Inventory;
import com.library.msalquiler.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling inventory-related operations.
 */
@RestController
@RequestMapping(path = "api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Constructor to initialize InventoryController with InventoryService.
     *
     * @param inventoryService The service responsible for inventory operations.
     */
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Get the list of all inventory items.
     *
     * @return List of Inventory objects representing inventory items.
     */
    @GetMapping
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();
    }

    /**
     * Get an inventory item by ID.
     *
     * @param id The ID of the inventory item.
     * @return ResponseEntity with the result of the inventory retrieval.
     */
    @GetMapping(path = "{inventoryId}")
    public ResponseEntity<Object> getInventoryById(@PathVariable("inventoryId") Long id) {
        return inventoryService.getInventoryById(id);
    }

    /**
     * Create a new inventory item.
     *
     * @param inventory The Inventory object containing information about the inventory item.
     * @return ResponseEntity with the result of the inventory item creation.
     */
    @PostMapping
    public ResponseEntity<Object> newInventory(@RequestBody Inventory inventory) {
        return inventoryService.newInventory(inventory);
    }

    /**
     * Edit an existing inventory item.
     *
     * @param id              The ID of the inventory item to be updated.
     * @param updatedInventory The updated Inventory object.
     * @return ResponseEntity with the result of the inventory item update.
     */
    @PutMapping(path = "{inventoryId}")
    public ResponseEntity<Object> editInventory(@PathVariable("inventoryId") Long id, @RequestBody Inventory updatedInventory) {
        return inventoryService.editInventory(id, updatedInventory);
    }

    /**
     * Update the stock of an inventory item.
     *
     * @param id      The ID of the inventory item to be updated.
     * @param newStock The new stock value.
     * @return ResponseEntity with the result of the stock update.
     */
    @PatchMapping(path = "{inventoryId}/update-stock")
    public ResponseEntity<Object> updateStock(@PathVariable("inventoryId") Long id, @RequestParam("stock") int newStock) {
        return inventoryService.updateStock(id, newStock);
    }

    /**
     * Delete an inventory item by ID.
     *
     * @param id The ID of the inventory item to be deleted.
     * @return ResponseEntity with the result of the inventory item deletion.
     */
    @DeleteMapping(path = "{inventoryId}")
    public ResponseEntity<Object> deleteInventory(@PathVariable("inventoryId") Long id) {
        return inventoryService.deleteInventory(id);
    }
}
