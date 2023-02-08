package com.example.backend.controllers;

import com.example.backend.dtos.EmployeeCreateDto;
import com.example.backend.models.Employee;
import com.example.backend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file) {
        return employeeService.uploadFile(file);
    }
}
