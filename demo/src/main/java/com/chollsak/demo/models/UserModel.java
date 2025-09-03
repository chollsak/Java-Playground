package com.chollsak.demo.models;

import com.chollsak.demo.dtos.UserDTO;

public class UserModel {
    private String id;
    private String name;
    private String email;
    private int age;

    public UserModel(){}

    public UserModel(String name, String email, int age){
        this.id = String.valueOf(System.currentTimeMillis());
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UserModel(UserDTO userDTO){
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.age = userDTO.getAge();
    }

    public UserModel(String id ,String name, String email, int age){
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }
}
