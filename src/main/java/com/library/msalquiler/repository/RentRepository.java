package com.library.msalquiler.repository;

import com.library.msalquiler.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Rent entities.
 */
@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    /**
     * Retrieves a Rent by its ID.
     *
     * @param id The ID of the Rent to retrieve.
     * @return An Optional containing the Rent, or empty if not found.
     */
    @Override
    Optional<Rent> findById(Long id);

    /**
     * Retrieves a list of Rents by their start date.
     *
     * @param startDate The start date of the Rents to retrieve.
     * @return A list of Rents matching the start date.
     */
    List<Rent> findByStartDate(LocalDate startDate);
}
