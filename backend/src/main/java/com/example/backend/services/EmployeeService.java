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

import java.io.BufferedReader;
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

    public ResponseEntity<Object> UploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        List<Employee> employees = new ArrayList<Employee>();

        try {
            assert fileName != null;
            String extension = FileManager.getFileExtension(fileName);

            if (!extension.equals("csv")) {
                throw new IllegalArgumentException("Only csv files are accepted.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));

            List<String[]> data = csvReader.readAll();

            for (String[] employee : data) {
                if (employee.length != 3) {
                    throw new IllegalArgumentException("Bad CSV file.");
                }

                employees.add(new Employee(UUID.randomUUID(), employee[0], employee[1], employee[2]));
            }

            for (Employee employee : employees) {
                System.out.println(employee.getId() + " " + employee.getName() + " " + employee.getEmail() + " " + employee.getPhoneNumber() + " ");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok("File uploaded successfully.");
    }
}
