package com.library.msalquiler.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity class representing the inventory information for a book.
 */
@Entity
@Table
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // The ID of the book associated with this inventory
    private Long book_id;

    // The current stock quantity of the book in inventory
    private int stock;

    // The date when the inventory information was last updated
    private LocalDate updated_at;

    /**
     * Default constructor for Inventory.
     */
    public Inventory() {
    }

    /**
     * Constructor for creating a new Inventory with an existing ID, book_id, stock, and updated_at.
     *
     * @param id         The ID of the inventory.
     * @param book_id    The ID of the book associated with this inventory.
     * @param stock      The current stock quantity of the book in inventory.
     * @param updated_at The date when the inventory information was last updated.
     */
    public Inventory(long id, Long book_id, int stock, LocalDate updated_at) {
        this.id = id;
        this.book_id = book_id;
        this.stock = stock;
        this.updated_at = updated_at;
    }

    /**
     * Constructor for creating a new Inventory with book_id, stock, and updated_at.
     *
     * @param book_id    The ID of the book associated with this inventory.
     * @param stock      The current stock quantity of the book in inventory.
     * @param updated_at The date when the inventory information was last updated.
     */
    public Inventory(Long book_id, int stock, LocalDate updated_at) {
        this.book_id = book_id;
        this.stock = stock;
        this.updated_at = updated_at;
    }

    // Getter and setter methods for each field

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }
}
