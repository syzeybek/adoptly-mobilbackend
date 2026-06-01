package com.adoptly.adoptly_mobilbackend.controller;

import com.adoptly.adoptly_mobilbackend.model.Animal;
import com.adoptly.adoptly_mobilbackend.repository.AnimalRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/animals")
@CrossOrigin(origins = "*") // CORS ayarı mobil ve web için de geçerli, efsane
public class AnimalController {

    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // 1. Tüm İlanları Getir (GET) - Ana sayfadaki PetCard'lar için
    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    // 2. Yeni İlan Ekle (POST)
    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    // 3. ID ile Tek Bir Hayvan Getir (GET) - PetDetail ekranı için
    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı: " + id));
    }

    // 4. İlan Bilgilerini Güncelle (PUT)
    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Long id, @RequestBody Animal animalDetails) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı: " + id));
        
        // Bütün alanları mobil uyumlu modelimize (breed, location) göre güncelliyoruz
        animal.setName(animalDetails.getName());
        animal.setBreed(animalDetails.getBreed()); // species -> breed yapıldı
        animal.setAge(animalDetails.getAge());
        animal.setGender(animalDetails.getGender());
        animal.setLocation(animalDetails.getLocation()); // city -> location yapıldı (Ve asıl koda eklendi!)
        animal.setStatus(animalDetails.getStatus());
        animal.setDescription(animalDetails.getDescription());
        animal.setImageUrl(animalDetails.getImageUrl());
        
        return animalRepository.save(animal);
    }

    // 5. İlanı Sil (DELETE)
    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı: " + id));
        
        animalRepository.delete(animal);
        return "İlan başarıyla silindi: " + id;
    }

    // 6. Filtreli Arama (GET) - Mobilde Cins (Breed) araması yapacağız
    @GetMapping("/search")
    public List<Animal> searchAnimals(@RequestParam(required = false) String breed) {
        if (breed != null) {
            return animalRepository.findAll().stream()
                    .filter(a -> a.getBreed().equalsIgnoreCase(breed)) // species -> breed yapıldı
                    .toList();
        }
        return animalRepository.findAll();
    }
}