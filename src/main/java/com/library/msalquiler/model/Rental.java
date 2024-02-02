package com.library.msalquiler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rentals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    @Column(name = "starts_at")
    private LocalDate startDate;

    @NotNull
    @Column(name = "ends_at")
    private LocalDate endDate;

    @Column(name = "returns_at")
    private LocalDate returnDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentalBook> rentalBooks;

    @ElementCollection
    @Column(name =  "book_id")
    private List<Long> books;
}
