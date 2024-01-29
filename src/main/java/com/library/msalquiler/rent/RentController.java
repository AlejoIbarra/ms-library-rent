package com.library.msalquiler.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //@PutMapping Falta editar

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
