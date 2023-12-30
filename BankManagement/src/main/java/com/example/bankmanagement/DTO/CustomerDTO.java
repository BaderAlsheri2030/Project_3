package com.example.bankmanagement.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private Integer user_id;
    @NotNull(message = "phone number cannot be null")
    @Pattern(regexp = "^05\\d{8}$", message = "must start with 05 and consists of 10 numbers")
    private String phoneNumber;
}
