package com.library.msalquiler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    private Long libId;
    private Long autId;
    private Long catId;
    private String libNombre;
    private double libPrecioAlquiler;
    private String libAnioPublicacion;
    private String libISBN;

}
