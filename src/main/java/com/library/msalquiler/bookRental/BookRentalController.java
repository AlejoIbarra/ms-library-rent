package com.library.msalquiler.bookRental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/books-rentals")
public class BookRentalController {

    private  final BookRentalService bookRentalService;

    @Autowired
    public BookRentalController(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    @GetMapping
    public List<BookRental> getBookRental(){
        return this.bookRentalService.getRentedBooks();
    }

    @PostMapping
    public ResponseEntity<Object> registerBookRental(@RequestBody BookRental bookRental){
        return this.bookRentalService.newBookRental(bookRental);

    }

    @DeleteMapping(path = "{bookRentalId}")
    public ResponseEntity<Object> delete(@PathVariable("bookRentalId") Long id){
        return this.bookRentalService.deleteBookRental(id);
    }


}
