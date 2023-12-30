package com.example.bankmanagement.Service;

import com.example.bankmanagement.DTO.AccountDTO;
import com.example.bankmanagement.Exception.ApiException;
import com.example.bankmanagement.Model.Account;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AccountRepository;
import com.example.bankmanagement.Repository.AuthRepository;
import com.example.bankmanagement.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    //user
    public void creatAccount(AccountDTO accountDTO,Integer auth){
        User user = authRepository.findUserById(auth);
        Customer customer = customerRepository.findCustomerById(user.getCustomer().getId());
        Account account = new Account(null,accountDTO.getAccountNumber(),0,false,customer);
        accountRepository.save(account);
    }
    //admin
    public void activateAccount(Integer account_id,Integer customer_id){
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null){
            throw new ApiException("Customer account not found");
        }
        if (customer.getAccounts().isEmpty()){
            throw new ApiException("Customer doesn't have accounts");
        }
        for(Account account: customer.getAccounts()){
            if (account.getId().equals(account_id)){
                account.setActive(true);
                accountRepository.save(account);
                break;
            }
        }

    }

    //customer
    public Account viewAccountDetails(Integer auth,Integer account_id){
        User user = authRepository.findUserById(auth);
        if(user == null){
            throw new ApiException("invalid");
        }
        Customer customer = customerRepository.findCustomerById(user.getCustomer().getId());
        if (customer == null){
            throw new ApiException("User is not a customer");
        }
        for (Account account: customer.getAccounts()){
            if (account.getId().equals(account_id)){
                return account;
            }
        }

        return null;
    }

    //customer
    public List<Account> myAccounts(Integer auth) {
        List<Account> myAccounts = new ArrayList<>();
        User user = authRepository.findUserById(auth);
        Customer customer = customerRepository.findCustomerById(user.getCustomer().getId());
        myAccounts = accountRepository.findAccountsByCustomerId(customer.getId());
        return myAccounts;
    }
    //customer

    public void deposit(Integer account_id,Integer auth,double value ){
        User user = authRepository.findUserById(auth);
        Customer customer = customerRepository.findCustomerById(user.getCustomer().getId());
        Account account = accountRepository.findAccountByCustomerId(account_id);
        if (!account.getCustomer().getId().equals(customer.getId())){
            throw new ApiException("Invalid account");
        }
        if (value < 1){
            throw new ApiException("Invalid value input");
        }
        if (!account.isActive()){
            throw new ApiException("sorry, your account is not active");
        }
        account.setBalance(account.getBalance()+value);
        accountRepository.save(account);
    }
    //customer

    public void withdraw(Integer account_id,Integer auth,double value ){
        User user = authRepository.findUserById(auth);
        Customer customer = customerRepository.findCustomerById(user.getCustomer().getId());
        Account account = accountRepository.findAccountByCustomerId(account_id);
        if (!account.getCustomer().getId().equals(customer.getId())){
            throw new ApiException("Invalid account");
        }
        if (value < 1){
            throw new ApiException("Invalid value input");
        }
        if (account.getBalance() < value){
            throw new ApiException("Invalid, the account balance is "+account.getBalance());
        }
        if (!account.isActive()){
            throw new ApiException("sorry, your account is not active");
        }
        account.setBalance(account.getBalance()-value);
        accountRepository.save(account);
    }

    //customer
    public void transfer(Integer account_id,Integer auth,String accountNumber,double value){
        User user = authRepository.findUserById(auth);
        Customer customer = customerRepository.findCustomerById(user.getCustomer().getId());
        Account account = accountRepository.findAccountByCustomerId(account_id);
        if (!account.getCustomer().getId().equals(customer.getId())){
            throw new ApiException("Invalid customer account");
        }
        if (!account.isActive()){
            throw new ApiException("sorry, your account is not active");
        }
        Account account2 = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account2 == null){
            throw new ApiException("Invalid account number");
        }
        if (account.getBalance() < value){
            throw new ApiException("Cannot complete transfer, the account balance is "+account.getBalance());
        }
        account.setBalance(account.getBalance()-value);
        account2.setBalance(account2.getBalance()+value);
        accountRepository.save(account);
        accountRepository.save(account2);
    }

    //admin
    public void freezeAccount(Integer id){
        Account account = accountRepository.findAccountById(id);
        if (account == null){
            throw new ApiException("account not found");
        }
        if (!account.isActive()){
            throw new ApiException("account is already blocked");
        }
        account.setActive(false);
    }



}
