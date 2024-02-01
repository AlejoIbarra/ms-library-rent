package com.library.msalquiler.repository;

import com.library.msalquiler.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository <Rent,Long> {
    @Override
    Optional<Rent> findById(Long aLong);

    List<Rent> findByStartDate(LocalDate startDate);

}
