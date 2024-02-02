package com.library.msalquiler.repository.jpa;

import com.library.msalquiler.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryJpaRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByBookId(Long bookId);

    @Query("SELECT i FROM Inventory i WHERE i.bookId = :bookId ORDER BY i.updatedAt DESC LIMIT 1")
    Optional<Inventory> findLatestByBookId(@Param("bookId") Long bookId);
}
