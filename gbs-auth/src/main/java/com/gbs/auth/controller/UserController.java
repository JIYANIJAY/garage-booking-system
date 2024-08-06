package com.gbs.auth.controller;

import com.gbs.auth.service.UserService;
import com.gbs.common.dto.UserDTO;
import com.gbs.common.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(new ApiResponse(LocalDateTime.now(),
                HttpStatus.CREATED,"register user successfully", userService.save(userDTO)), HttpStatus.CREATED);
    }
}
