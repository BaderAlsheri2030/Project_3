package com.example.bankmanagement.Service;

import com.example.bankmanagement.DTO.EmployeeDTO;
import com.example.bankmanagement.Exception.ApiException;
import com.example.bankmanagement.Model.Employee;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthRepository;
import com.example.bankmanagement.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final AuthRepository authRepository;
    private final EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees(){
       return employeeRepository.findAll();
    }

    //admin
    public void addEmployee(EmployeeDTO employeeDTO, Integer id){
        User user = authRepository.findUserById(id);
        Employee employee = new Employee(null,employeeDTO.getPosition(),employeeDTO.getSalary(),user);
        employeeRepository.save(employee);
    }

    //admin
    public void updateEmployee(Integer id,EmployeeDTO employee){
        Employee employee1 = employeeRepository.findEmployeeById(id);
        if (employee1 == null){
            throw new ApiException("Employee not found");
        }
        employee1.setPosition(employee.getPosition());
        employee1.setSalary(employee.getSalary());
        employeeRepository.save(employee1);

    }

    //admin
    public void deleteEmployee(Integer id){
    Employee employee = employeeRepository.findEmployeeById(id);
    if (employee == null){
        throw new ApiException("invalid id input");
    }
    employeeRepository.delete(employee);
    }


}
