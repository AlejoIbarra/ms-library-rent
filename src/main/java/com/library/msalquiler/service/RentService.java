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

/**
 * Service class for managing Rent entities.
 */
@Service
public class RentService {

    private final RentRepository rentRepository;
    private final HashMap<String, Object> data = new HashMap<>();

    /**
     * Constructor for RentService.
     *
     * @param rentRepository The repository for Rent entities.
     */
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

    /**
     * Updates an existing rent.
     *
     * @param rentId      ID of the rent to be updated.
     * @param updatedRent Rent with the updated information.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> updateRent(Long rentId, Rent updatedRent) {
        Optional<Rent> optionalRent = rentRepository.findById(rentId);

        return optionalRent.map(existingRent -> {
            existingRent.setStartDate(updatedRent.getStartDate());
            existingRent.setEndDate(updatedRent.getEndDate());
            existingRent.setReturn_date(updatedRent.getReturn_date());
            existingRent.setPenalty_fee(updatedRent.getPenalty_fee());

            Rent savedRent = rentRepository.save(existingRent);

            return buildResponse(savedRent, "Rent updated successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Rent with the given ID not found", HttpStatus.NOT_FOUND));
    }

    /**
     * Registers the return date for a rented book.
     *
     * @param rentId ID of the rent for which the return date is to be registered.
     * @return ResponseEntity with the operation result.
     */
    public ResponseEntity<Object> patchReturnDate(Long rentId) {
        Optional<Rent> optionalRent = rentRepository.findById(rentId);

        return optionalRent.map(existingRent -> {
            existingRent.setReturn_date(LocalDate.now());
            Rent savedRent = rentRepository.save(existingRent);

            return buildResponse(savedRent, "Return date registered successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Rent with the given ID not found", HttpStatus.NOT_FOUND));
    }

    /**
     * Searches for rents with a specified start date.
     *
     * @param startDate Start date for searching rents.
     * @return List of rents with the specified start date.
     */
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

    /**
     * Helper method to build a successful response entity.
     *
     * @param body    Object to be included in the response body.
     * @param message Success message.
     * @param status  HTTP status code.
     * @return ResponseEntity with the successful response.
     */
    private ResponseEntity<Object> buildResponse(Object body, String message, HttpStatus status) {
        return ResponseEntity.status(status).body(body);
    }

    /**
     * Helper method to build an error response entity.
     *
     * @param message Error message.
     * @param status  HTTP status code.
     * @return ResponseEntity with the error response.
     */
    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(message);
    }

    /**
     * Helper method to build a conflict response entity.
     *
     * @param errorMessage Conflict error message.
     * @return ResponseEntity with the conflict response.
     */
    private ResponseEntity<Object> conflictResponse(String errorMessage) {
        data.put("error", true);
        data.put("message", errorMessage);
        return new ResponseEntity<>(data, HttpStatus.CONFLICT);
    }
}
