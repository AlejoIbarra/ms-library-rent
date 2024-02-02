package com.library.msalquiler.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rental_books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="rental_id")
    private Rental rental;

    @Column(name = "book_id")
    private Long bookId;

    @NotNull
    @PositiveOrZero
    @Column(name = "cost")
    private double cost;

}
