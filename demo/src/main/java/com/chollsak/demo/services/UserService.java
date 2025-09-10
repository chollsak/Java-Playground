package com.chollsak.demo.services;

import com.chollsak.demo.dtos.AdminDTO;
import com.chollsak.demo.dtos.CustomerDTO;
import com.chollsak.demo.dtos.CustomerRequestDTO;
import com.chollsak.demo.entities.Customer;
import com.chollsak.demo.entities.User;
import com.chollsak.demo.enums.Role;
import com.chollsak.demo.repositories.UserRepository;
import com.chollsak.demo.dtos.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    public UserDTO createUser(String email, Role role){
        log.info("Create user");
        User userToFind = findUserByEmail(email);
        if(userToFind != null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users already exist");
        }

        User user = new User();
        user.setEmail(email);
        user.setRole(role);
        User savedUser = userRepository.save(user);

        if(role.equals(Role.CUSTOMER)){

            CustomerRequestDTO customer = new CustomerRequestDTO();
            customer.setUser_id(savedUser.getId());
            customerService.createCustomer(customer);

        } else if (role.equals(Role.ADMIN)) {
            AdminDTO admin = new AdminDTO();
            admin.setUserId(savedUser.getId());
            adminService.createAdmin(admin);
        }

        return new UserDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
    }

    public User findUserByEmail(String email){
        log.info("Find user by email");
        User user = userRepository.findByEmail(email).orElse(null);
        return user;
    }

    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        return new UserDTO(user.getId(), user.getEmail(),user.getRole());
    }

//    public List<UserDTO> getAllUser(){
//        log.info("Get all users");
//        List <UserDTO> users = userRepository.findAll().stream()
//                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getAge()))
//                .toList();
//        log.info("Found {} users",users.size());
//        return users;
//    }


}
