package com.library.msalquiler.controller;

import com.library.msalquiler.service.BookRentalService;
import com.library.msalquiler.model.BookRental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling book rental operations.
 */
@RestController
@RequestMapping(path = "api/v1/books-rentals")
public class BookRentalController {

    private final BookRentalService bookRentalService;

    /**
     * Constructor to initialize BookRentalController with BookRentalService.
     *
     * @param bookRentalService The service responsible for book rental operations.
     */
    @Autowired
    public BookRentalController(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    /**
     * Get the list of rented books.
     *
     * @return List of BookRental objects representing rented books.
     */
    @GetMapping
    public List<BookRental> getBookRental() {
        return this.bookRentalService.getRentedBooks();
    }

    /**
     * Register a new book rental.
     *
     * @param bookRental The BookRental object containing information about the rental.
     * @return ResponseEntity with the result of the book rental registration.
     */
    @PostMapping
    public ResponseEntity<Object> registerBookRental(@RequestBody BookRental bookRental) {
        return this.bookRentalService.newBookRental(bookRental);
    }

    /**
     * Delete a book rental by its ID.
     *
     * @param id The ID of the book rental to be deleted.
     * @return ResponseEntity with the result of the book rental deletion.
     */
    @DeleteMapping(path = "{bookRentalId}")
    public ResponseEntity<Object> delete(@PathVariable("bookRentalId") Long id) {
        return this.bookRentalService.deleteBookRental(id);
    }
}
