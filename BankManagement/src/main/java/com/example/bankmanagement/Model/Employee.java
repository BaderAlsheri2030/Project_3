package com.example.bankmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(100) not null")
    @NotNull(message = "position cannot be null")
    private String position;
    @Column(columnDefinition = "double not null")
    @NotNull(message = "salary cannot be null")
    @Positive(message = "must be non negative")
    private double salary;


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

}