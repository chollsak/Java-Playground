package com.chollsak.demo.dtos;

public class UserRequestDTO {
    private String name;
    private String email;
    private int age;
    public UserRequestDTO(){

    }

    public UserRequestDTO(String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
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

}
