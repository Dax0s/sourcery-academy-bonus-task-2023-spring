package com.example.backend.services;

import com.example.backend.mybatis.repositories.EmployeeRepository;
import com.example.backend.utility.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Object> UploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        try {
            assert fileName != null;
            String extension = FileManager.getFileExtension(fileName);

            if (!extension.equals("csv")) {
                throw new IllegalArgumentException("Only csv files are accepted.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }



        return ResponseEntity.ok("File uploaded successfully.");
    }
}
