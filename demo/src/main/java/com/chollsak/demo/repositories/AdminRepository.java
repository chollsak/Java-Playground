package com.chollsak.demo.repositories;

import com.chollsak.demo.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByUserId(UUID userId);

}
