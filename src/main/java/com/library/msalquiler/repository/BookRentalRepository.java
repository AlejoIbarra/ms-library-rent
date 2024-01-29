package com.library.msalquiler.repository;

import com.library.msalquiler.model.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRentalRepository extends JpaRepository <BookRental, Long> {

    @Override
    Optional<BookRental> findById(Long aLong);
}
