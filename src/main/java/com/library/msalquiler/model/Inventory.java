package com.library.msalquiler.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long book_id;

    private int stock;

    private LocalDate updated_at;

    public Inventory() {
    }

    public Inventory(long id, Long book_id, int stock, LocalDate updated_at) {
        this.id = id;
        this.book_id = book_id;
        this.stock = stock;
        this.updated_at = updated_at;
    }

    public Inventory(Long book_id, int stock, LocalDate updated_at) {
        this.book_id = book_id;
        this.stock = stock;
        this.updated_at = updated_at;
    }


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
