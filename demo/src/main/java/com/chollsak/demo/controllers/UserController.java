package com.chollsak.demo.controllers;

import com.chollsak.demo.dtos.ApiResponse;
import com.chollsak.demo.dtos.UserDTO;
import com.chollsak.demo.dtos.UserRequestDTO;
import com.chollsak.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ApiResponse<List<UserDTO>> getUser(){
        List<UserDTO> users = userService.getAllUser();
        String message = "success";
        if (users.isEmpty()){
            message = "No users in database";
        }
        return new ApiResponse<>(users, message);
    }

    @GetMapping("/users/{id}")
    public ApiResponse<UserDTO> getUserById(@PathVariable String id){
        UserDTO user = userService.getUserById(id);
        return new ApiResponse<>(user, "success");
    }

    @PostMapping("/users")
    public ApiResponse<UserDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){
        UserDTO user = userService.createUser(userRequestDTO.getName(),userRequestDTO.getEmail(),userRequestDTO.getAge());
        return new ApiResponse<>(user, "created");
    }

    @DeleteMapping("/users/{id}")
    public ApiResponse<String> deleteUserById(@PathVariable String id){
        String response = userService.deleteUserById(id);
        return new ApiResponse<>(response);
    }

    @PutMapping("/users/{id}")
    public ApiResponse<String> updateUser(@PathVariable String id, @RequestBody UserRequestDTO userRequestDTO){
        String response = userService.updateUserById(id, userRequestDTO);
        return new ApiResponse<>(response);
    }
}
