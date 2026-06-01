package com.adoptly.adoptly_mobilbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*") // Frontend erişimi için gerekli
public class HomeController {

    // Direkt /api adresine girildiğinde çalışır
    @GetMapping("/api")
    public String home() {
        return "Adoptly API is up and running! 🐾";
    }

    // Ana dizine (/) girildiğinde çalışması için (Opsiyonel)
    @GetMapping("/")
    public String welcome() {
        return "Adoptly Backend'e Hoş Geldiniz! API için /api adresini kullanın.";
    }
}