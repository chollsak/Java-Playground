package com.chollsak.demo.dtos;

public class UserDTO {
    private String id;
    private String name;
    private String email;
    private int age;

    public UserDTO(){

    }

    public UserDTO(String id, String name, String email, int age){
        this.id = id;
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

    public String getId() {
        return id;
    }
}
