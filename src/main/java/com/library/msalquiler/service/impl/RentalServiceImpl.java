package com.library.msalquiler.service.impl;

import com.library.msalquiler.facade.BooksFacade;
import com.library.msalquiler.model.*;
import com.library.msalquiler.model.request.RentalRequest;
import com.library.msalquiler.repository.ClientRepository;
import com.library.msalquiler.repository.InventoryRepository;
import com.library.msalquiler.repository.RentalBookRepository;
import com.library.msalquiler.repository.RentalRepository;
import com.library.msalquiler.service.RentalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final ClientRepository clientRepository;
    private final RentalBookRepository rentalBookRepository;
    private final InventoryRepository inventoryRepository;
    private final HashMap<String, Object> data = new HashMap<>();
    private BooksFacade booksFacade;

    /**
     * Retrieves all rentals stored in the system.
     *
     * @return List of rentals.
     */
    public List<Rental> getRentals(LocalDate startDate) {
        if (startDate != null) {
            return rentalRepository.findByStartDate(startDate);
        }
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.isEmpty() ? null : rentals;
    }

    /**
     * Retrieves a rental by ID.
     *
     * @param id ID of the rental to be retrieved.
     * @return ResponseEntity with the operation result.
     */
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rental not found"));
    }

    /**
     * Creates a new rental.
     *
     * @param request Rental request to be created.
     * @return ResponseEntity with the operation result.
     */
    @Transactional
    public ResponseEntity<Object> newRental(Long clientId, RentalRequest request) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            List<Book> books = request.getBooks().stream().map(booksFacade::getBook).filter(Objects::nonNull).toList();

            if (books.size() != request.getBooks().size()) {
                data.put("data", books);
                data.put("message", "Books not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
            } else {
                Rental rental = Rental.builder()
                        .client(client.get())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .createdAt(LocalDateTime.now())
                        .build();
                rentalRepository.save(rental); // Guarda primero el Rental

                List<RentalBook> rentalBooks = new ArrayList<>();
                for (Book book : books) {
                    Optional<Inventory> inventory = inventoryRepository.findLatestByBookId(book.getLibId());
                    if (inventory.isPresent() && inventory.get().getStock() > 0) {
                        RentalBook rentalBook = RentalBook.builder()
                                .rental(rental)
                                .bookId(book.getLibId())
                                .cost(book.getLibPrecioAlquiler())
                                .build();
                        rentalBooks.add(rentalBook);
                        Inventory updated = new Inventory();
                        updated.setBookId(book.getLibId());
                        updated.setStock(inventory.get().getStock() - 1);
                        updated.setUpdatedAt(LocalDateTime.now());
                        inventoryRepository.save(updated);
                    } else {
                        data.put("data", book);
                        data.put("message", "Book without stock");
                    }
                }
                rental.setRentalBooks(rentalBooks);
                Rental newRental = rentalRepository.save(rental);

                if (newRental != null) {
                    data.put("data", rental);
                    data.put("message", "Rental created successfully");
                    return ResponseEntity.status(HttpStatus.CREATED).body(data);
                } else {
                    data.put("data", rental);
                    data.put("message", "Rental cannot be created");
                    return null;
                }
            }
        } else {
            throw new RuntimeException("Client not found with id: " + clientId);
        }
    }

    public ResponseEntity<Object> updateRental(Long rentId, Rental updatedRental) {
        Optional<Rental> optionalRent = rentalRepository.findById(rentId);

        return optionalRent.map(existingRent -> {
            existingRent.setStartDate(updatedRental.getStartDate());
            existingRent.setEndDate(updatedRental.getEndDate());
            existingRent.setReturnDate(updatedRental.getReturnDate());

            Rental savedRental = rentalRepository.save(existingRent);

            return buildResponse(savedRental, "Rental updated successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Rental with the given id not found", HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<Object> buildResponse(Object body, String message, HttpStatus status) {
        return ResponseEntity.status(status).body(body);
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(message);
    }

    public ResponseEntity<Object> patchReturnDate(Long rentId) {
        Optional<Rental> optionalRent = rentalRepository.findById(rentId);

        return optionalRent.map(existingRent -> {
            existingRent.setReturnDate(LocalDate.now());
            Rental savedRental = rentalRepository.save(existingRent);

            return buildResponse(savedRental, "Return date registered successfully", HttpStatus.OK);
        }).orElseGet(() -> buildErrorResponse("Rental with the given id not found", HttpStatus.NOT_FOUND));
    }


    public List<Rental> searchRentalsByStartDate(LocalDate startDate) {
        return rentalRepository.findByStartDate(startDate);
    }

    /**
     * Deletes a rent by its ID.
     *
     * @param id ID of the rent to be deleted.
     * @return ResponseEntity with the operation result.
     */
    @Transactional
    public ResponseEntity<Object> deleteRental(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isEmpty()) {
            return conflictResponse("No such rental exists");
        }
        for (RentalBook rentalBook : rental.get().getRentalBooks()) {
            Optional<Inventory> inventory = inventoryRepository.findLatestByBookId(rentalBook.getBookId());
            if (inventory.isPresent()) {
                Inventory updated = new Inventory();
                updated.setBookId(rentalBook.getBookId());
                updated.setStock(inventory.get().getStock() + 1);
                updated.setUpdatedAt(LocalDateTime.now());
                inventoryRepository.save(updated);
            } else {
                return conflictResponse("No such inventory exists");
            }
        }
        rentalRepository.delete(rental.get());
        data.put("message", "Deleted successfully");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
    }

    private ResponseEntity<Object> conflictResponse(String errorMessage) {
        data.put("error", true);
        data.put("message", errorMessage);
        return new ResponseEntity<>(data, HttpStatus.CONFLICT);
    }

}
