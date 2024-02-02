package com.library.msalquiler.controller;

import com.library.msalquiler.model.Rent;
import com.library.msalquiler.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller class for handling rent-related operations.
 */
@RestController
@RequestMapping(path = "api/v1/rents")
public class RentController {

    private final RentService rentService;

    /**
     * Constructor to initialize RentController with RentService.
     *
     * @param rentService The service responsible for rent operations.
     */
    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    /**
     * Get the list of all rents.
     *
     * @return List of Rent objects representing rents.
     */
    @GetMapping
    public List<Rent> getRents() {
        return this.rentService.getRents();
    }

    /**
     * Register a new rent.
     *
     * @param rent The Rent object containing information about the rent.
     * @return ResponseEntity with the result of the rent registration.
     */
    @PostMapping
    public ResponseEntity<Object> registerRent(@RequestBody Rent rent) {
        return this.rentService.newRent(rent);
    }

    /**
     * Update an existing rent.
     *
     * @param id          The ID of the rent to be updated.
     * @param updatedRent The updated Rent object.
     * @return ResponseEntity with the result of the rent update.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRent(@PathVariable Long id, @RequestBody Rent updatedRent) {
        return rentService.updateRent(id, updatedRent);
    }

    /**
     * Search rents based on the start date.
     *
     * @param startDate The start date for searching rents.
     * @return ResponseEntity with a list of rents matching the start date.
     */
    @GetMapping("/rents_date")
    public ResponseEntity<List<Rent>> searchRentsByStartDate(@RequestParam("startDate") LocalDate startDate) {
        List<Rent> rents = rentService.searchRentsByStartDate(startDate);
        return ResponseEntity.ok(rents);
    }

    /**
     * Patch the return date of a rent.
     *
     * @param id The ID of the rent to be updated.
     * @return ResponseEntity with the result of the return date patching.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchReturnDate(@PathVariable Long id) {
        return rentService.patchReturnDate(id);
    }

    /**
     * Delete a rent by ID.
     *
     * @param id The ID of the rent to be deleted.
     * @return ResponseEntity with the result of the rent deletion.
     */
    @DeleteMapping(path = "{rentId}")
    public ResponseEntity<Object> delete(@PathVariable("rentId") Long id) {
        return this.rentService.deleteRent(id);
    }
}
