package com.example.bankmanagement.Controller;

import com.example.bankmanagement.DTO.CustomerDTO;
import com.example.bankmanagement.DTO.EmployeeDTO;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get-all-employees")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    @PostMapping("/register-employee/{id}")
    public ResponseEntity registerEmployee(@PathVariable Integer id , @RequestBody @Valid EmployeeDTO employeeDTO){
            employeeService.addEmployee(employeeDTO,id);
        return ResponseEntity.status(200).body("Employee Registered");
    }

    @PutMapping("/update-employee/{}")
    public ResponseEntity updateEmployee(@PathVariable Integer id ,@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.updateEmployee(id,employeeDTO);
        return ResponseEntity.status(200).body("Employee updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(200).body("Employee Deleted");
    }
}
