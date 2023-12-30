package com.example.bankmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(19) not null")
    @Pattern(regexp = "^10\\d{2}-\\d{4}-\\d{4}-\\d{4}$",message = "account number must start with 10 and consists of 16 numbers")
    @NotNull(message = "account number cannot be null")
    private String accountNumber;
    @Column(columnDefinition = "double not null")
    @NotNull(message = "balance cannot be null")
    @Min(value = 0,message = "balance cannot be negative ")
    private double balance;
    @AssertFalse
    private boolean isActive;


    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;
}
