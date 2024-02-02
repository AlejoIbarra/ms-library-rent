package com.library.msalquiler.repository;

import com.library.msalquiler.model.RentalBook;
import com.library.msalquiler.repository.jpa.RentalBookJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RentalBookRepository {

    private final RentalBookJpaRepository repository;

    public void saveAll(List<RentalBook> rentalBooks) { repository.saveAll(rentalBooks); }

    public void deleteByRentalId(Long rentalId) { repository.deleteByRentalId(rentalId); }
}
