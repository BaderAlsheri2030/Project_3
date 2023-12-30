package com.example.bankmanagement.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    @NotNull(message = "salary cannot be null")
    @Positive(message = "must be non negative")
    private double salary;

    private Integer user_id;

    @NotNull(message = "position cannot be null")
    private String position;



}
