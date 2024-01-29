package com.library.msalquiler.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository <Rent,Long> {
    @Override
    Optional<Rent> findById(Long aLong);
}
