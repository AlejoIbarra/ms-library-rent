package com.library.msalquiler.service;

import com.library.msalquiler.model.BookRental;
import com.library.msalquiler.repository.BookRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing BookRental entities.
 */
@Service
public class BookRentalService {

    private final BookRentalRepository bookRentalRepository;

    /**
     * Constructor for BookRentalService.
     *
     * @param bookRentalRepository The repository for BookRental entities.
     */
    @Autowired
    public BookRentalService(BookRentalRepository bookRentalRepository) {
        this.bookRentalRepository = bookRentalRepository;
    }

    /**
     * Retrieves a list of all rented books.
     *
     * @return A list of BookRental entities representing rented books.
     */
    public List<BookRental> getRentedBooks() {
        return bookRentalRepository.findAll();
    }

    /**
     * Creates a new book rental record.
     *
     * @param bookRental The BookRental entity to be created.
     * @return ResponseEntity with status and message indicating the result of the operation.
     */
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

    /**
     * Deletes a book rental record by its ID.
     *
     * @param id The ID of the BookRental entity to be deleted.
     * @return ResponseEntity with status and message indicating the result of the operation.
     */
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
