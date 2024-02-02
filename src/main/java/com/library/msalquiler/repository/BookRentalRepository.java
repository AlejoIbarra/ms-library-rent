package com.library.msalquiler.repository;

import com.library.msalquiler.model.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing BookRental entities.
 */
@Repository
public interface BookRentalRepository extends JpaRepository<BookRental, Long> {

    /**
     * Retrieves a BookRental by its ID.
     *
     * @param id The ID of the BookRental to retrieve.
     * @return An Optional containing the BookRental, or empty if not found.
     */
    @Override
    Optional<BookRental> findById(Long id);
}
