package com.library.msalquiler.service;

import com.library.msalquiler.model.BookRental;
import com.library.msalquiler.repository.BookRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookRentalService {

    private final BookRentalRepository bookRentalRepository;

    @Autowired
    public BookRentalService(BookRentalRepository bookRentalRepository) {
        this.bookRentalRepository = bookRentalRepository;
    }

    public List<BookRental> getRentedBooks() {
        return bookRentalRepository.findAll();
    }

    public ResponseEntity<Object> newBookRental(BookRental bookRental) {
        if (bookRentalRepository.existsById(bookRental.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("The record already exists");
        }

        try {
            bookRentalRepository.save(bookRental);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving the record in the database");
        }
    }

    public ResponseEntity<Object> deleteBookRental(Long id) {
        if (!bookRentalRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("The record does not exist");
        }

        bookRentalRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Successfully deleted");
    }
}
