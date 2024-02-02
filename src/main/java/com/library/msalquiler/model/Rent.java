package com.library.msalquiler.model;

import com.library.msalquiler.model.BookRental;
import com.library.msalquiler.model.Client;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Entity class representing information about a book rental.
 */
@Entity
@Table
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // The start date of the book rental
    private LocalDate startDate;

    // The end date of the book rental
    private LocalDate endDate;

    // The return date of the book rental
    private LocalDate return_date;

    // The penalty fee associated with the book rental
    private float penalty_fee;

    @OneToMany(mappedBy = "rent")
    private List<BookRental> bookRentals;

    /**
     * Default constructor for Rent.
     */
    public Rent() {
    }

    /**
     * Constructor for creating a new Rent with client, start date, end date, return date, penalty fee, and book rentals.
     *
     * @param client        The client associated with the book rental.
     * @param startDate     The start date of the book rental.
     * @param endDate       The end date of the book rental.
     * @param return_date   The return date of the book rental.
     * @param penalty_fee   The penalty fee associated with the book rental.
     * @param bookRentals   The list of book rentals associated with this rent.
     */
    public Rent(Client client, LocalDate startDate, LocalDate endDate, LocalDate return_date, float penalty_fee, List<BookRental> bookRentals) {
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.return_date = return_date;
        this.penalty_fee = penalty_fee;
        this.bookRentals = bookRentals;
    }

    /**
     * Constructor for creating a new Rent with an existing ID, client, start date, end date, return date, penalty fee, and book rentals.
     *
     * @param id            The ID of the rent.
     * @param client        The client associated with the book rental.
     * @param startDate     The start date of the book rental.
     * @param endDate       The end date of the book rental.
     * @param return_date   The return date of the book rental.
     * @param penalty_fee   The penalty fee associated with the book rental.
     * @param bookRentals   The list of book rentals associated with this rent.
     */
    public Rent(Long id, Client client, LocalDate startDate, LocalDate endDate, LocalDate return_date, float penalty_fee, List<BookRental> bookRentals) {
        this.id = id;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.return_date = return_date;
        this.penalty_fee = penalty_fee;
        this.bookRentals = bookRentals;
    }

    // Getter and setter methods for each field

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public float getPenalty_fee() {
        return penalty_fee;
    }

    public void setPenalty_fee(float penalty_fee) {
        this.penalty_fee = penalty_fee;
    }

    public List<BookRental> getBookRentals() {
        return bookRentals;
    }

    public void setBookRentals(List<BookRental> bookRentals) {
        this.bookRentals = bookRentals;
    }
}
