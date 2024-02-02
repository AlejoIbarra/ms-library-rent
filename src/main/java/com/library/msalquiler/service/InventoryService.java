package com.library.msalquiler.service;

import com.library.msalquiler.model.Inventory;
import com.library.msalquiler.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Inventory entities.
 */
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final HashMap<String, Object> data;

    /**
     * Constructor for InventoryService.
     *
     * @param inventoryRepository The repository for Inventory entities.
     */
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.data = new HashMap<>();
    }

    /**
     * Retrieves all inventory records stored in the system.
     *
     * @return List of inventory records.
     */
    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    /**
     * Retrieves an inventory record by ID.
     *
     * @param id ID of the inventory record to be retrieved.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> getInventoryById(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory.map(value -> buildResponse(value, "Inventory found successfully", HttpStatus.OK))
                .orElseGet(() -> buildErrorResponse("Inventory with the provided ID not found", HttpStatus.NOT_FOUND));
    }

    /**
     * Creates a new inventory record.
     *
     * @param inventory Inventory record to be created.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> newInventory(Inventory inventory) {
        Optional<Inventory> existingInventory = inventoryRepository.findById(inventory.getId());
        return existingInventory.map(value -> buildErrorResponse("ID already exists", HttpStatus.CONFLICT))
                .orElseGet(() -> {
                    inventoryRepository.save(inventory);
                    return buildResponse(inventory, "Successfully created", HttpStatus.CREATED);
                });
    }

    /**
     * Updates an existing inventory record.
     *
     * @param id               ID of the inventory record to be updated.
     * @param updatedInventory Inventory record with the updated information.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> editInventory(Long id, Inventory updatedInventory) {
        Optional<Inventory> existingInventory = inventoryRepository.findById(id);

        return existingInventory.map(inventory -> {

            inventory.setStock(updatedInventory.getStock());

            inventoryRepository.save(inventory);

            return buildResponse(inventory, "Inventory updated successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Inventory with the given ID not found", HttpStatus.NOT_FOUND));
    }

    /**
     * Updates the stock of an existing inventory record.
     *
     * @param id      ID of the inventory record to be updated.
     * @param newStock New stock value.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> updateStock(Long id, int newStock) {
        Optional<Inventory> existingInventory = inventoryRepository.findById(id);

        return existingInventory.map(inventory -> {
            inventory.setStock(newStock);

            inventoryRepository.save(inventory);

            return buildResponse(inventory, "Stock updated successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Inventory with the given ID not found", HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes an inventory record by ID.
     *
     * @param id ID of the inventory record to be deleted.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> deleteInventory(Long id) {
        Optional<Inventory> existingInventory = inventoryRepository.findById(id);

        return existingInventory.map(inventory -> {

            inventoryRepository.delete(inventory);

            return buildResponse(null, "Inventory deleted successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Inventory with the given ID not found", HttpStatus.NOT_FOUND));
    }

    /**
     * Helper method to build a successful response entity.
     *
     * @param responseData Object to be included in the response body.
     * @param message      Success message.
     * @param status       HTTP status code.
     * @return ResponseEntity with the successful response.
     */
    private ResponseEntity<Object> buildResponse(Object responseData, String message, HttpStatus status) {
        data.clear();
        data.put("data", responseData);
        data.put("message", message);
        return ResponseEntity.status(status).body(data);
    }

    /**
     * Helper method to build an error response entity.
     *
     * @param errorMessage Error message.
     * @param status       HTTP status code.
     * @return ResponseEntity with the error response.
     */
    private ResponseEntity<Object> buildErrorResponse(String errorMessage, HttpStatus status) {
        data.clear();
        data.put("error", true);
        data.put("message", errorMessage);
        return ResponseEntity.status(status).body(data);
    }
}
