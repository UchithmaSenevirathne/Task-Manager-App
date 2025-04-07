package com.example.backend.services;

import com.example.backend.dtos.UserDTO;

public interface UserService {
    int saveUser(UserDTO userDTO);

    UserDTO loadUserDetailsByUsername(String username);
}
