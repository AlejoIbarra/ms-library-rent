package com.library.msalquiler.service;

import com.library.msalquiler.model.Inventory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryService {

    List<Inventory> getInventory();

    ResponseEntity<Object> getInventoryById(Long id);

    ResponseEntity<Object> newInventory(Inventory inventory);

    ResponseEntity<Object> editInventory(Long id, Inventory updatedInventory);

    ResponseEntity<Object> updateStock(Long id, int newStock);

    ResponseEntity<Object> deleteInventory(Long id);
}
