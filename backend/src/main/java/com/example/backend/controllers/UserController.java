package com.example.backend.controllers;

import com.example.backend.dtos.AuthDTO;
import com.example.backend.dtos.ResponseDTO;
import com.example.backend.dtos.UserDTO;
import com.example.backend.services.impl.UserServiceImpl;
import com.example.backend.util.JwtUtil;
import com.example.backend.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backend/user")
@CrossOrigin
public class UserController {
    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    //constructor injection
    public UserController(JwtUtil jwtUtil, UserServiceImpl userService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO> registerUser(
            @RequestPart("username") String username,
            @RequestPart("password") String password
    ) {

        try {
            UserDTO userDTO = new UserDTO();

            userDTO.setUsername(username);
            userDTO.setPassword(password);

            int res = userService.saveUser(userDTO);

            switch (res) {
                case VarList.Created -> {
                    System.out.println("save control-switch");
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setUsername(userDTO.getUsername());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
