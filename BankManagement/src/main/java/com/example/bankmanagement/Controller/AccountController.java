package com.example.bankmanagement.Controller;


import com.example.bankmanagement.DTO.AccountDTO;
import com.example.bankmanagement.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/get-all-accounts")
    public ResponseEntity getAllAccounts(){
        return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }

    @PostMapping("/create-account")
    public ResponseEntity createAccount(@RequestBody @Valid AccountDTO accountDTO,@AuthenticationPrincipal Integer auth){
        accountService.creatAccount(accountDTO,auth);
        return ResponseEntity.status(200).body("Account created");
    }

    @PutMapping("/activate-account/{acc_id}/customer/{customer_id}")
    public ResponseEntity activateAccount(@PathVariable Integer acc_id,@PathVariable Integer customer_id){
        accountService.activateAccount(acc_id,customer_id);
        return ResponseEntity.status(200).body("Account activated");
    }
    @GetMapping("/view-my-account-details/{id}")
    public ResponseEntity viewMyAccountDetails(@PathVariable Integer id,@AuthenticationPrincipal Integer auth){
        return ResponseEntity.status(200).body(accountService.viewAccountDetails(auth,id));
    }
    @GetMapping("/my-accounts")
    public ResponseEntity viewMyAccounts(@AuthenticationPrincipal Integer auth){
        return ResponseEntity.status(200).body(accountService.myAccounts(auth));
    }

    @PutMapping("/deposit/{amount}/to/{acc_id}")
    public ResponseEntity deposit(@PathVariable Integer acc_id,@PathVariable double amount,@AuthenticationPrincipal Integer auth){
        accountService.deposit(acc_id,auth,amount);
        return ResponseEntity.status(200).body("Amount Deposited");
    }

    @PutMapping("/withdraw/{amount}/from/{acc_id}")
    public ResponseEntity withdraw(@PathVariable Integer acc_id,@PathVariable double amount,@AuthenticationPrincipal Integer auth){
        accountService.withdraw(acc_id,auth,amount);
        return ResponseEntity.status(200).body("Amount withdrawn");
    }

    @PutMapping("/transfer/{amount}/from/{acc_id}/to/{account_number}")
    public ResponseEntity transfer(@PathVariable Integer acc_id,@PathVariable String account_number,@PathVariable double amount,@AuthenticationPrincipal Integer auth){
        accountService.transfer(acc_id,auth,account_number,amount);
        return ResponseEntity.status(200).body("Amount Transferred");
    }

    @PutMapping("freeze/{acc_id}")
    public ResponseEntity freezeAccount(@PathVariable Integer acc_id){
        accountService.freezeAccount(acc_id);
        return ResponseEntity.status(200).body("Account is blocked");
    }







}
