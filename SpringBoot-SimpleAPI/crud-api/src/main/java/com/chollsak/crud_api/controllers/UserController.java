package com.chollsak.crud_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("users")
    public String getUsers(){
        return "Chollsak, Nitcharat, Marcus Rashford";
    }

    @GetMapping("about")
    public String showAbout(){
        return "This is about";
    }

    @GetMapping("starter")
    public Integer showNumber(){
        return 1234;
    }

}
