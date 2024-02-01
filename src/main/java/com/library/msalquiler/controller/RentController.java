package com.library.msalquiler.controller;

import com.library.msalquiler.model.Rent;
import com.library.msalquiler.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rents")
public class RentController {

    private final RentService rentService;

    @Autowired
    public RentController(RentService rentService){
        this.rentService=rentService;
    }

    @GetMapping
    public List<Rent> getRents(){
        return this.rentService.getRents();
    }
    @PostMapping
    public ResponseEntity<Object> registerRent(@RequestBody Rent rent){
        return this.rentService.newRent(rent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRent(@PathVariable Long id, @RequestBody Rent updatedRent) {
        return rentService.updateRent(id, updatedRent);
    }
    @GetMapping("/rents_date")

    public ResponseEntity<List<Rent>> searchRentsByStartDate(@RequestParam("startDate") LocalDate startDate) {
        List<Rent> rents = rentService.searchRentsByStartDate(startDate);
        return ResponseEntity.ok(rents);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchReturnDate(@PathVariable Long id) {
        return rentService.patchReturnDate(id);
    }
/**
    @DeleteMapping(path = "rentId")
    public ResponseEntity<Object> delete(@PathVariable("rentId") Long id){
        return this.rentService.deleteRent(id);
    }

 */

    @DeleteMapping(path = "{rentId}")
    public ResponseEntity<Object> delete(@PathVariable("rentId") Long id){
        return this.rentService.deleteRent(id);
    }
    }
