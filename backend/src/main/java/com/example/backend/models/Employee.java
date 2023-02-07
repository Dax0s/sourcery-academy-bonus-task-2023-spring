package com.example.backend.models;

import com.example.backend.dtos.EmployeeCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;

    public Employee(EmployeeCreateDto dto, UUID id) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.id = id;
    }
}
