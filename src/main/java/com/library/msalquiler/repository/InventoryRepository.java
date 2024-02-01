package com.library.msalquiler.repository;

import com.library.msalquiler.model.Client;
import com.library.msalquiler.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findById(Long aLong);
}
