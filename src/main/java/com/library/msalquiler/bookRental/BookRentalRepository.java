package com.library.msalquiler.bookRental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRentalRepository extends JpaRepository <BookRental, Long> {

    @Override
    Optional<BookRental> findById(Long aLong);
}
