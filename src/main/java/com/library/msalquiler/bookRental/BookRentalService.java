package com.library.msalquiler.bookRental;

import com.library.msalquiler.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BookRentalService {

    private final BookRentalRepository bookRentalRepository;

    private final HashMap<String, Object> data = new HashMap<>();

    @Autowired
    public BookRentalService(BookRentalRepository bookRentalRepository){
        this.bookRentalRepository = bookRentalRepository;
    }

    public List<BookRental> getRentedBooks(){
        return this.bookRentalRepository.findAll();
    }

    public ResponseEntity<Object> newBookRental (BookRental bookRental) {
        Optional<BookRental> res = bookRentalRepository.findById(bookRental.getId());

        if (res.isPresent()) {
            data.put("error", true);
            data.put("message", "Ya está creado el registro");
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }

        bookRentalRepository.save(bookRental);
        data.put("data", bookRental);
        data.put("message", "Se creó exitosamente");

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteBookRental(@PathVariable("bookRentalId") Long id) {
        boolean exist = this.bookRentalRepository.existsById(id);
        if (!exist) {
            data.put("error", true);
            data.put("message", "No existe el registro");
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }
        bookRentalRepository.deleteById(id);
        data.put("message", "Eliminado exitosamente");

        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }
}
