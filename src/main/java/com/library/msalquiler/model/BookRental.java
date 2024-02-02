package com.library.msalquiler.model;

import jakarta.persistence.*;

/**
 * Entity class representing the details of a book rental.
 */
@Entity
@Table
public class BookRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="rent_id")
    private Rent rent;

    // Represents the ID of the book
    private Long libro_id;

    // Represents the cost associated with the book rental
    private float cost;

    /**
     * Default constructor for BookRental.
     */
    public BookRental() {
    }

    /**
     * Constructor for creating a new BookRental with libro_id and cost.
     *
     * @param libro_id The ID of the book.
     * @param cost     The cost associated with the book rental.
     */
    public BookRental(Long libro_id, float cost) {
        this.libro_id = libro_id;
        this.cost = cost;
    }

    /**
     * Constructor for creating a new BookRental with an existing ID, libro_id, and cost.
     *
     * @param id       The ID of the book rental.
     * @param libro_id The ID of the book.
     * @param cost     The cost associated with the book rental.
     */
    public BookRental(Long id, Long libro_id, float cost) {
        this.id = id;
        this.libro_id = libro_id;
        this.cost = cost;
    }

    /**
     * Getter method for retrieving the ID of the book rental.
     *
     * @return The ID of the book rental.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for setting the ID of the book rental.
     *
     * @param id The ID to be set for the book rental.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for retrieving the associated rent object.
     *
     * @return The Rent object associated with the book rental.
     */
    public Rent getRent() {
        return rent;
    }

    /**
     * Setter method for setting the associated rent object.
     *
     * @param rent The Rent object to be associated with the book rental.
     */
    public void setRent(Rent rent) {
        this.rent = rent;
    }

    /**
     * Getter method for retrieving the ID of the book.
     *
     * @return The ID of the book.
     */
    public Long getLibro_id() {
        return libro_id;
    }

    /**
     * Setter method for setting the ID of the book.
     *
     * @param libro_id The ID of the book to be set.
     */
    public void setLibro_id(Long libro_id) {
        this.libro_id = libro_id;
    }

    /**
     * Getter method for retrieving the cost associated with the book rental.
     *
     * @return The cost associated with the book rental.
     */
    public float getCost() {
        return cost;
    }

    /**
     * Setter method for setting the cost associated with the book rental.
     *
     * @param cost The cost to be set for the book rental.
     */
    public void setCost(float cost) {
        this.cost = cost;
    }
}
