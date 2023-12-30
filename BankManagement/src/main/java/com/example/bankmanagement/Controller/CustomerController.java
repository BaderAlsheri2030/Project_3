package com.example.bankmanagement.Controller;

import com.example.bankmanagement.DTO.CustomerDTO;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get-all-customers")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @PostMapping("/register")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerDTO customerDTO, @AuthenticationPrincipal User user){
        customerService.addCustomer(customerDTO,user.getId());
        return ResponseEntity.status(200).body("customer Registered");
    }

    @PutMapping("/update-my-info")
    public ResponseEntity customerUpdate(@RequestBody @Valid CustomerDTO customerDTO,@AuthenticationPrincipal User user){
        customerService.updateCustomer(customerDTO, user.getId());
        return ResponseEntity.status(200).body("customer updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("customer Deleted");
    }
}
