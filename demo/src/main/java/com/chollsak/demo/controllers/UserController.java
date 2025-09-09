package com.chollsak.demo.controllers;

import com.chollsak.demo.dtos.ApiResponse;
import com.chollsak.demo.dtos.UserDTO;
import com.chollsak.demo.dtos.UserRequestDTO;
import com.chollsak.demo.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

//    @GetMapping("/users")
//    public ApiResponse<List<UserDTO>> getUser(){
//        List<UserDTO> users = userService.getAllUser();
//        String message = "success";
//        if(users == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found");
//        }
//        return new ApiResponse<>(users, message);
//    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        UserDTO user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/users")
    public ApiResponse<UserDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserDTO user = userService.createUser(userRequestDTO.getEmail(), userRequestDTO.getRole());
        return new ApiResponse<>(user, "created");
    }




}
