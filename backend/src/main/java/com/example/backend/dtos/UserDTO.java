package com.example.backend.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
}
