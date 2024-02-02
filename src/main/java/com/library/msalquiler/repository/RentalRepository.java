package com.library.msalquiler.repository;

import com.library.msalquiler.model.Rental;
import com.library.msalquiler.repository.jpa.RentalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalRepository {

    private final RentalJpaRepository repository;

    public Rental save(Rental rental) { return repository.save(rental); }

    public List<Rental> findAll() {
        return repository.findAll();
    }

    public Optional<Rental> findById(Long id) { return repository.findById(id); }

    public boolean existsById(Long id) { return repository.existsById(id); }

    public void deleteById(Long id) { repository.deleteById(id); }

    public void delete(Rental rental) {
        repository.delete(rental);
    }

    public List<Rental> findByStartDate(LocalDate startDate) { return repository.findByStartDate(startDate); }

}
