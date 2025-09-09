package com.chollsak.demo.controllers;

import com.chollsak.demo.dtos.CustomerDTO;
import com.chollsak.demo.dtos.CustomerRequestDTO;
import com.chollsak.demo.dtos.CustomerUpdateRequestDTO;
import com.chollsak.demo.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping("/users/customers")
    public CustomerDTO createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerDTO customer = customerService.createCustomer(customerRequestDTO);
        return customer;
    }

    @PutMapping("/users/customers/{id}")
    public CustomerDTO updateCustomer(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO,
                                      @PathVariable Long id) {
        return customerService.updateCustomer(customerUpdateRequestDTO, null, id);
    }

    @PutMapping("/users/customers/{id}/profile-image")
    public CustomerDTO updateProfileImage(@PathVariable Long id,
                                          @RequestParam("file") MultipartFile file) {
        return customerService.updateCustomer(new CustomerUpdateRequestDTO(), file, id);
    }


}
