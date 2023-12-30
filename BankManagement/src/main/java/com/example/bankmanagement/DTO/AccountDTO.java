package com.example.bankmanagement.DTO;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {
    @NotNull(message = "balance cannot be null")
    @Min(value = 0,message = "balance cannot be negative ")
    private double balance;

    private Integer customer_id;

    @AssertFalse(message = "account will be activated by the system")
    private boolean isActive;

    @Pattern(regexp = "^10\\d{2}-\\d{4}-\\d{4}-\\d{4}$",message = "account number must start with 10 and consists of 16 numbers")
    @NotNull(message = "account number cannot be null")
    private String accountNumber;


}
