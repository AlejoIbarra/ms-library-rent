package com.library.msalquiler.bookRental;


import com.library.msalquiler.rent.Rent;
import jakarta.persistence.*;

@Entity
@Table
public class BookRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="rent_id")
    private Rent rent;

    private Long libro_id;

    private float cost;

    public BookRental() {
    }

    public BookRental(Long libro_id, float cost) {
        this.libro_id = libro_id;
        this.cost = cost;
    }

    public BookRental(Long id, Long libro_id, float cost) {
        this.id = id;
        this.libro_id = libro_id;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Long getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(Long libro_id) {
        this.libro_id = libro_id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }


}
