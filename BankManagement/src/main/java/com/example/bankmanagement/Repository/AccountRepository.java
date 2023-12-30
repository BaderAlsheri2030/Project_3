package com.example.bankmanagement.Repository;

import com.example.bankmanagement.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AccountRepository extends JpaRepository<Account,Integer> {

    List<Account> findAccountsByCustomerId(Integer id);
    Account findAccountByCustomerId(Integer id);
    Account findAccountById(Integer id);
    Account findAccountByAccountNumber(String number);

}
