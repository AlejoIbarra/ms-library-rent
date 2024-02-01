package com.library.msalquiler.service;

import com.library.msalquiler.model.Rent;
import com.library.msalquiler.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final HashMap<String, Object> data = new HashMap<>();

    @Autowired
    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    /**
     * Retrieves all rents stored in the system.
     *
     * @return List of rents.
     */
    public List<Rent> getRents() {
        return rentRepository.findAll();
    }

    /**
     * Creates a new rent.
     *
     * @param rent Rent to be created.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> newRent(Rent rent) {
        if (rentRepository.existsById(rent.getId())) {
            return conflictResponse("The ID is already in use");
        }

        rentRepository.save(rent);
        data.put("data", rent);
        data.put("message", "Book rented successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    // Add method to update Rent if


    public ResponseEntity<Object> updateRent(Long rentId, Rent updatedRent) {
        Optional<Rent> optionalRent = rentRepository.findById(rentId);

        return optionalRent.map(existingRent -> {
            existingRent.setStartDate(updatedRent.getStartDate());
            existingRent.setEndDate(updatedRent.getEndDate());
            existingRent.setReturn_date(updatedRent.getReturn_date());
            existingRent.setPenalty_fee(updatedRent.getPenalty_fee());

            Rent savedRent = rentRepository.save(existingRent);

            return buildResponse(savedRent, "Rent updated successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Rent with the given id not found", HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<Object> buildResponse(Object body, String message, HttpStatus status) {
        return ResponseEntity.status(status).body(body);
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(message);
    }

    public ResponseEntity<Object> patchReturnDate(Long rentId) {
        Optional<Rent> optionalRent = rentRepository.findById(rentId);

        return optionalRent.map(existingRent -> {
            existingRent.setReturn_date(LocalDate.now());
            Rent savedRent = rentRepository.save(existingRent);

            return buildResponse(savedRent, "Return date registered successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Rent with the given id not found", HttpStatus.NOT_FOUND));
    }


    public List<Rent> searchRentsByStartDate(LocalDate startDate) {
        return rentRepository.findByStartDate(startDate);
    }
    /**
     * Deletes a rent by its ID.
     *
     * @param id ID of the rent to be deleted.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> deleteRent(Long id) {
        if (!rentRepository.existsById(id)) {
            return conflictResponse("No such rent exists");
        }

        rentRepository.deleteById(id);
        data.put("message", "Deleted successfully");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
    }

    private ResponseEntity<Object> conflictResponse(String errorMessage) {
        data.put("error", true);
        data.put("message", errorMessage);
        return new ResponseEntity<>(data, HttpStatus.CONFLICT);
    }

}
