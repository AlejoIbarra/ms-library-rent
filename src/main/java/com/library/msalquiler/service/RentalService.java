package com.library.msalquiler.service;

import com.library.msalquiler.model.Rental;
import com.library.msalquiler.model.request.RentalRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface RentalService {

    List<Rental> getRentals(LocalDate startDate);

    Rental getRentalById(Long rentalId);

    ResponseEntity<Object> newRental(Long clientId, RentalRequest request);

    ResponseEntity<Object> updateRental(Long rentId, Rental updatedRental);

    ResponseEntity<Object> patchReturnDate(Long rentId);

    List<Rental> searchRentalsByStartDate(LocalDate startDate);

    ResponseEntity<Object> deleteRental(Long id);
}
