package com.adoptly.adoptly_mobilbackend.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "applications")
public class Application {

    // Supabase 'id' sütununu UUID olarak bekliyor. 
    // GenerationType.UUID sayesinde yeni kayıtlarda otomatik karmaşık şifre üretilecek.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Supabase'deki auth.users tablosuna bağlı olduğu için UUID formatında olmalı.
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    // Java'da 'animalId' demeye devam edeceğiz ama veritabanındaki 'pet_id' sütununa yazacak.
    @Column(name = "pet_id", nullable = false)
    private Long animalId;

    // Supabase'de 'note' sütunu yok, ama Hibernate projeyi başlatırken otomatik ekleyecek.
    private String note;

    // Supabase'de default 'pending' olarak ayarlanmış, Java'da da tutarlılık için ekliyoruz.
    private String status = "PENDING";

    public Application() {
    }

    // --- GETTER VE SETTER METOTLARI ---

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}