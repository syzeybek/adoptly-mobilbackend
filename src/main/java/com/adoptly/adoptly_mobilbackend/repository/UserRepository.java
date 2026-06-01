package com.adoptly.adoptly_mobilbackend.repository;

import com.adoptly.adoptly_mobilbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    // Spring Data JPA temel veritabanı işlemlerini arka planda halleder.
}