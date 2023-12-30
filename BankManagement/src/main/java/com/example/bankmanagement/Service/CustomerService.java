package com.example.bankmanagement.Service;

import com.example.bankmanagement.DTO.CustomerDTO;
import com.example.bankmanagement.Exception.ApiException;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthRepository;
import com.example.bankmanagement.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    //admin
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    //customer
    public void addCustomer(CustomerDTO customerDTO,Integer id){
        User user = authRepository.findUserById(id);
        Customer customer = new Customer(null,customerDTO.getPhoneNumber(),user,null);
        customerRepository.save(customer);
    }
    //customer

    public void updateCustomer(CustomerDTO customerDTO,Integer auth){
        User user = authRepository.findUserById(auth);
        Customer customer = customerRepository.findCustomerById(user.getCustomer().getId());
    if (customer == null){
        throw new ApiException("Invalid not found");
    }
    else if (!customer.getUser().getId().equals(auth)){
        throw new ApiException("Sorry, You cannot update this customer");
    }
    customer.setPhoneNumber(customerDTO.getPhoneNumber());
    customerRepository.save(customer);
    }
    //admin
    public void deleteCustomer(Integer id){
    Customer customer = customerRepository.findCustomerById(id);
        if(customer==null){
            throw new ApiException("customer Not Found");
        }
        customerRepository.delete(customer);
    }
}
