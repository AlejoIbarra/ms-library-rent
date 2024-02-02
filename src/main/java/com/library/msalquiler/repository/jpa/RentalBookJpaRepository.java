package com.library.msalquiler.repository.jpa;

import com.library.msalquiler.model.RentalBook;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface RentalBookJpaRepository  extends JpaRepository<RentalBook, Long> {

    @Modifying
    @Transactional
    void deleteByRentalId(Long rentalId);
}
