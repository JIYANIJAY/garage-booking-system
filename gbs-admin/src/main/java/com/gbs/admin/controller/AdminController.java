package com.gbs.admin.controller;

import com.gbs.admin.service.AdminService;
import com.gbs.common.UserDTOS;
import com.gbs.common.UserServiceGrpc;
import com.gbs.common.dto.UserDTO;
import com.gbs.common.web.ApiResponse;
import com.google.protobuf.Descriptors;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(new ApiResponse(LocalDateTime.now(),
                HttpStatus.CREATED,"register user successfully", adminService.createUser(userDTO)), HttpStatus.CREATED);
    }




}
