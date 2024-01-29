package com.library.msalquiler.rent;


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

    private final HashMap <String,Object> data = new HashMap<>();

    @Autowired
    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public List<Rent> getRents(){
        return this.rentRepository.findAll();
    }

    public ResponseEntity<Object> newRent(Rent rent){
        Optional<Rent> res = rentRepository.findById(rent.getId());

        if(res.isPresent()){
            data.put("error",true);
            data.put("message","El id ya esta en uso");
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }

        rentRepository.save(rent);
        data.put("data",rent);
        data.put("message","Se rento el libro exitosamente");

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }


    //Falta editar

    public ResponseEntity<Object> deleteRent(Long id) {
        boolean exist=this.rentRepository.existsById(id);
        if (!exist){
            data.put("error", true);
            data.put("message", "No existe esa renta");
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }
        rentRepository.deleteById(id);
        data.put("message","Eliminado con exito");

        return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
    }
}
