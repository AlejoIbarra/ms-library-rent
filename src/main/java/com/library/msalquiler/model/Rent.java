package com.library.msalquiler.model;

import com.library.msalquiler.model.BookRental;
import com.library.msalquiler.model.Client;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")

    private Client client;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate return_date;
    private float penalty_fee;

    @OneToMany(mappedBy = "rent")
    private List<BookRental> bookRentals;

    //-----------------------------------
    //Constructors
    //-----------------------------------

    public Rent() {
    }

    public Rent(Client client, LocalDate startDate, LocalDate endDate, LocalDate return_date, float penalty_fee, List<BookRental> bookRentals) {
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.return_date = return_date;
        this.penalty_fee = penalty_fee;
        this.bookRentals = bookRentals;
    }

    public Rent(Long id, Client client, LocalDate startDate, LocalDate endDate, LocalDate return_date, float penalty_fee, List<BookRental> bookRentals) {
        this.id = id;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.return_date = return_date;
        this.penalty_fee = penalty_fee;
        this.bookRentals = bookRentals;
    }

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
