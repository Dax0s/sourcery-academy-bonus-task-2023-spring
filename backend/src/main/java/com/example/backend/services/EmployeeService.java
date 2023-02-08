package com.example.backend.services;

import com.example.backend.models.Employee;
import com.example.backend.mybatis.repositories.EmployeeRepository;
import com.example.backend.utility.FileManager;
import com.opencsv.CSVReader;
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

    public void createEmployee(Employee employee) {
        employeeRepository.createEmployee(employee);
    }

    public void createEmployee(List<Employee> employees) {
        for (Employee employee : employees) {
            employeeRepository.createEmployee(employee);
        }
    }

    public ResponseEntity<Object> uploadFile(MultipartFile file) {
        List<Employee> employees = new ArrayList<>();

        try {
            if (!FileManager.checkIfFileIsCsv(file)) {
                throw new IllegalArgumentException("Not a CSV file");
            }

            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
            List<String[]> data = csvReader.readAll();

            int amountOfColumnsRequired = 3;
            for (String[] employee : data) {
                if (employee.length == 1 && employee[0].equals("")) {
                    continue;
                } else if (employee.length != amountOfColumnsRequired) {
                    System.out.println(employee.length);
                    throw new IllegalArgumentException("Bad CSV file.");
                }

                employees.add(new Employee(UUID.randomUUID(), employee[0], employee[1], employee[2]));
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        employeeRepository.clearDatabase();
        createEmployee(employees);

        return new ResponseEntity<>("File uploaded successfully.", HttpStatus.OK);
    }
}
