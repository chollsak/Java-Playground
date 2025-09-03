package com.chollsak.demo.services;

import com.chollsak.demo.daos.UserDAO;
import com.chollsak.demo.dtos.UserDTO;
import com.chollsak.demo.dtos.UserRequestDTO;
import com.chollsak.demo.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<UserDTO> getAllUser(){
        return userDAO.getUsers().stream().map(user -> new UserDTO(user.getId(),user.getName(),user.getEmail(),user.getAge())).toList();
    }

    public UserDTO getUserById(String id){
        return userDAO.getUsers().stream().filter(user -> user.getId().equals(id)).map(user -> new UserDTO(user.getId(),user.getName(),user.getEmail(),user.getAge())).findFirst().orElse(null);
    }

    public UserDTO createUser(String name, String email, int age){
        UserModel user = userDAO.addUser(new UserModel(name, email, age));
        return getUserById(user.getId());
    }

    public String deleteUserById(String id){
        UserModel userToDelete = userDAO.getUsers().stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if(userToDelete != null){
            userDAO.removeUser(userToDelete);
            return "User deleted";
        }
        return "User not found";
    }

    public String updateUserById(String id, UserRequestDTO userRequestDTO){
        UserModel userToUpdate = new UserModel(id, userRequestDTO.getName(), userRequestDTO.getEmail(), userRequestDTO.getAge());
        return userDAO.updateUser(userToUpdate);
    }
}
