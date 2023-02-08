package com.example.backend.models;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
}
