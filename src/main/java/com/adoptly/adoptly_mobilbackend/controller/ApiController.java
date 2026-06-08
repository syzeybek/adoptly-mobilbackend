package com.adoptly.adoptly_mobilbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    // Sadece /api adresine girildiğinde çalışacak fonksiyon
    @GetMapping
    public ResponseEntity<Map<String, Object>> welcome() {
        Map<String, Object> response = new LinkedHashMap<>();
        
        // Ekrana basılacak o şık ve profesyonel bilgiler
        response.put("message", "Adoptly REST API'ye Hoş Geldiniz! 🐾");
        response.put("status", "🟢 UP (Sistem Aktif)");
        response.put("version", "v1.0.0");
        response.put("developer", "Yiğit Zeybek");
        response.put("documentation", "GitHub sayfasındaki README.md dosyasını inceleyin.");
        
        return ResponseEntity.ok(response);
    }
}