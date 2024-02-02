package com.library.msalquiler.service.impl;

import com.library.msalquiler.model.Inventory;
import com.library.msalquiler.repository.InventoryRepository;
import com.library.msalquiler.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final HashMap<String, Object> data;

    @Autowired
    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
        this.data = new HashMap<>();
    }

    private ResponseEntity<Object> buildResponse(Object responseData, String message, HttpStatus status) {
        data.clear();
        data.put("data", responseData);
        data.put("message", message);
        return ResponseEntity.status(status).body(data);
    }

    private ResponseEntity<Object> buildErrorResponse(String errorMessage, HttpStatus status) {
        data.clear();
        data.put("error", true);
        data.put("message", errorMessage);
        return ResponseEntity.status(status).body(data);
    }

    public List<Inventory> getInventory() {
        return repository.findAll();
    }

    public ResponseEntity<Object> getInventoryById(Long id) {
        Optional<Inventory> inventory = repository.findById(id);
        return inventory.map(value -> buildResponse(value, "Inventory found successfully", HttpStatus.OK))
                .orElseGet(() -> buildErrorResponse("Inventory with the provider id not found", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> newInventory(Inventory inventory) {
        Optional<Inventory> existingInventory = repository.findById(inventory.getId());
        return existingInventory.map(value -> buildErrorResponse("ID already exists", HttpStatus.CONFLICT))
                .orElseGet(() -> {
                    repository.save(inventory);
                    return buildResponse(inventory, "Successfully created", HttpStatus.CREATED);
                });
    }

    public ResponseEntity<Object> editInventory(Long id, Inventory updatedInventory) {
        Optional<Inventory> existingInventory = repository.findById(id);

        return existingInventory.map(inventory -> {

            inventory.setStock(updatedInventory.getStock());

            repository.save(inventory);

            return buildResponse(inventory, "Inventory updated successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Inventory with the given id not found", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> updateStock(Long id, int newStock) {
        Optional<Inventory> existingInventory = repository.findById(id);

        return existingInventory.map(inventory -> {
            inventory.setStock(newStock);

            repository.save(inventory);

            return buildResponse(inventory, "Stock updated successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Inventory with the given id not found", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> deleteInventory(Long id) {
        Optional<Inventory> existingInventory = repository.findById(id);

        return existingInventory.map(inventory -> {

            repository.delete(inventory);

            return buildResponse(null, "Inventory deleted successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Inventory with the given id not found", HttpStatus.NOT_FOUND));
    }
}
