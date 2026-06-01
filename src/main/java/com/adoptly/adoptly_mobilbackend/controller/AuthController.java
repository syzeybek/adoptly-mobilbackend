package com.adoptly.adoptly_mobilbackend.controller;

import com.adoptly.adoptly_mobilbackend.model.User;
import com.adoptly.adoptly_mobilbackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Yeni Kullanıcı Kaydı (Register)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Şifreyi kaydetmeden önce (eğer istersen) şifreleme vs. burada yapılabilir
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Kayıt başarısız: " + e.getMessage());
        }
    }

    // Tüm Kullanıcıları Listele (Gerekirse)
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}