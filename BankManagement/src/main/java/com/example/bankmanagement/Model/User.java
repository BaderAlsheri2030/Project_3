package com.example.bankmanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username Cannot be null")
    @Column(columnDefinition = "varchar(100) not null unique")
    @Size(min =4,max = 10, message = "username must be between 4-10 characters")
    private String username;
    @NotEmpty(message = "Name Cannot be null")
    @Column(columnDefinition = "varchar(100) not null unique")
    @Size(min =2,max = 20, message = "name must be between 2-20 characters")
    private String name;
    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(100) not null unique")
    @Min(value = 6,message = "password must be at least 6 characters")
    private String password;
    @Column(columnDefinition = "varchar(100) not null unique")
    @NotNull(message = "email cannot be null")
    @Email(message = "must be a valid email")
    private String email;
    @Column(columnDefinition = "varchar(8) check(role ='CUSTOMER' or role = 'EMPLOYEE', role = 'ADMIN')")
    @NotNull(message = "role cannot be null")
    private String role;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}