package com.example.backend.services;

import com.example.backend.models.Employee;
import com.example.backend.mybatis.repositories.EmployeeRepository;
import com.example.backend.utility.FileManager;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import org.apache.catalina.connector.InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public ResponseEntity<Object> uploadFile(MultipartFile file) {
        List<Employee> employees = new ArrayList<>();

        try {
            if (!FileManager.checkIfFileIsCsv(file)) {
                return new ResponseEntity<>("Not a CSV file", HttpStatus.BAD_REQUEST);
            }

            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));

            List<String[]> data = csvReader.readAll();

            for (String[] employee : data) {
                if (employee.length != 3) {
                    throw new IllegalArgumentException("Bad CSV file.");
                }

                employees.add(new Employee(UUID.randomUUID(), employee[0], employee[1], employee[2]));
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        employeeRepository.clearDatabase();
        for (Employee employee : employees) {
            employeeRepository.createEmployee(employee);
        }

        return ResponseEntity.ok("File uploaded successfully.");
    }
}
