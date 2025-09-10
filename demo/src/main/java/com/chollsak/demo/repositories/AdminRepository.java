package com.chollsak.demo.repositories;

import com.chollsak.demo.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUserId(Long userId);

}
