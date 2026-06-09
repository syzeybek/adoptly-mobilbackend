package com.adoptly.adoptly_mobilbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") // Frontend erişimi için gerekli
public class HomeController {

    // 🚀 GÜNCELLENDİ: /api adresine girildiğinde profesyonel JSON döner
    @GetMapping("/api")
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new LinkedHashMap<>();
        
        response.put("message", "Adoptly REST API'ye Hoş Geldiniz! 🐾");
        response.put("status", "🟢 UP (Sistem Aktif)");
        response.put("version", "v1.0.0");
        response.put("developer", "Yiğit Zeybek");
        response.put("documentation", "GitHub sayfasındaki README.md dosyasını inceleyin.");
        
        return ResponseEntity.ok(response);
    }

    // Ana dizine (/) girildiğinde çalışması için (Mevcut kodun korundu)
    @GetMapping("/")
    public String welcome() {
        return "Adoptly Backend'e Hoş Geldiniz! API için /api adresini kullanın.";
    }
}