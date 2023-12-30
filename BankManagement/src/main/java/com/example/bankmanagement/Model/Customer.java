package com.example.bankmanagement.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotNull(message = "phone number cannot be null")
    @Pattern(regexp = "^05\\d{8}$", message = "must start with 05 and consists of 10 numbers")
    private String phoneNumber;


    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private Set<Account> accounts;

}
