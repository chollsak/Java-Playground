package com.chollsak.demo.services;

import com.chollsak.demo.dtos.CustomerDTO;
import com.chollsak.demo.dtos.CustomerRequestDTO;
import com.chollsak.demo.dtos.CustomerUpdateRequestDTO;
import com.chollsak.demo.entities.Customer;
import com.chollsak.demo.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public CustomerDTO createCustomer(CustomerRequestDTO customerRequestDTO){
        Customer isCustomer = getCustomerByUserId(customerRequestDTO.getUser_id());
        if(isCustomer != null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer already exist");
        }

        Customer customer = new Customer();
        customer.setFirstName(customerRequestDTO.getFirstName());
        customer.setLastName(customerRequestDTO.getLastName());
        customer.setShopName(customerRequestDTO.getFirstName());
        customer.setDateOfBirth(customerRequestDTO.getDateOfBirth());
        customer.setPoints(0);
        customer.setUserId(customerRequestDTO.getUser_id());

        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerDTO(savedCustomer);
    }

    public Customer getCustomerByUserId(Long id){
        Customer customer = customerRepository.findByUserId(id).orElse(null);
        return customer;
    }

    public CustomerDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO, MultipartFile profileImage, Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer doesn't exist");
        }

        if (customerUpdateRequestDTO.getFirstName() != null) {
            customer.setFirstName(customerUpdateRequestDTO.getFirstName());
        }
        if (customerUpdateRequestDTO.getLastName() != null) {
            customer.setLastName(customerUpdateRequestDTO.getLastName());
        }
        if (customerUpdateRequestDTO.getShopName() != null) {
            customer.setShopName(customerUpdateRequestDTO.getShopName());
        }
        if (customerUpdateRequestDTO.getDateOfBirth() != null) {
            customer.setDateOfBirth(customerUpdateRequestDTO.getDateOfBirth());
        }

        if (profileImage != null && !profileImage.isEmpty()) {
            // Delete old image if exists
            if (customer.getImgPath() != null) {
                fileStorageService.deleteFile(customer.getImgPath());
            }
            // Upload new image
            String newImageKey = fileStorageService.uploadProfileImage(profileImage, id);
            customer.setImgPath(newImageKey);
        }


        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerDTO(savedCustomer);
    }
}
