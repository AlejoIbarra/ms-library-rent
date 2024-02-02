package com.library.msalquiler.repository.jpa;

import com.library.msalquiler.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalJpaRepository extends JpaRepository<Rental, Long>, JpaSpecificationExecutor<Rental> {

    @Override
    @NonNull
    Optional<Rental> findById(@NonNull Long aLong);

    List<Rental> findByStartDate(LocalDate startDate);
}
