package com.example.backend.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeCreateDto {
    private String name;
    private String email;
    private String phoneNumber;
}
