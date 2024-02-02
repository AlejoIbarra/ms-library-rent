package com.library.msalquiler.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.msalquiler.model.Client;
import com.library.msalquiler.model.RentalBook;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequest {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotEmpty
    @ElementCollection
    private List<Long> books;
}
