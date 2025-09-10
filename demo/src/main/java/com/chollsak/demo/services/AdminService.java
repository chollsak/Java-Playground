package com.chollsak.demo.services;

import com.chollsak.demo.dtos.AdminDTO;
import com.chollsak.demo.entities.Admin;
import com.chollsak.demo.repositories.AdminRepository;
import com.chollsak.demo.repositories.ProductImgRepository;
import com.chollsak.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {
    @Autowired
    public AdminRepository adminRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public ProductImgRepository productImgRepository;

    @Autowired
    public FileStorageService fileStorageService;

    public Admin createAdmin(AdminDTO adminDTO){
        Admin isAdmin = getAdminByUserId(adminDTO.getUserId());
        if(isAdmin != null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin already exist");
        }

        Admin admin = new Admin();
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setUserId(adminDTO.getUserId());

        return adminRepository.save(admin);

    }

    public Admin getAdminByUserId(Long id){
        Admin admin = adminRepository.findByUserId(id).orElse(null);
        return admin;
    }


}
