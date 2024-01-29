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

    private LocalDate start_date;
    private LocalDate end_date;
    private LocalDate return_date;
    private float penalty_fee;

    @OneToMany(mappedBy="rent")
    private List<BookRental> bookRentals;

    //-----------------------------------
    //Constructors
    //-----------------------------------

    public Rent() {
    }

    public Rent(Client client, LocalDate start_date, LocalDate end_date, LocalDate return_date, float penalty_fee) {
        this.client = client;
        this.start_date = start_date;
        this.end_date = end_date;
        this.return_date = return_date;
        this.penalty_fee = penalty_fee;
    }

    //----------------------------------
    //Methods
    //----------------------------------

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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
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
}
