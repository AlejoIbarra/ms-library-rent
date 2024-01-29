package com.library.msalquiler.service;

import com.library.msalquiler.model.Rent;
import com.library.msalquiler.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    // Add method to update Rent if needed

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
