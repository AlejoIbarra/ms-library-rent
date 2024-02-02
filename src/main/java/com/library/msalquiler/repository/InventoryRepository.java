package com.library.msalquiler.repository;

import com.library.msalquiler.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Inventory entities.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Retrieves an Inventory by its ID.
     *
     * @param id The ID of the Inventory to retrieve.
     * @return An Optional containing the Inventory, or empty if not found.
     */
    Optional<Inventory> findById(Long id);
}
