package com.library.msalquiler.repository;

import com.library.msalquiler.model.Inventory;
import com.library.msalquiler.repository.jpa.InventoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InventoryRepository {

    private final InventoryJpaRepository repository;

    public Optional<Inventory> findById(Long aLong) { return repository.findById(aLong); }

    public Optional<Inventory> findLatestByBookId(Long bookId) { return repository.findLatestByBookId(bookId); }

    public List<Inventory> findAll() { return repository.findAll(); }

    public void save(Inventory inventory) { repository.save(inventory); }

    public void delete(Inventory inventory) { repository.delete(inventory); }
}
