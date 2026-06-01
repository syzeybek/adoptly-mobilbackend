package com.adoptly.adoptly_mobilbackend.controller;

import com.adoptly.adoptly_mobilbackend.model.Application;
import com.adoptly.adoptly_mobilbackend.model.Animal;
import com.adoptly.adoptly_mobilbackend.model.User;
import com.adoptly.adoptly_mobilbackend.repository.ApplicationRepository;
import com.adoptly.adoptly_mobilbackend.repository.AnimalRepository;
import com.adoptly.adoptly_mobilbackend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    public ApplicationController(ApplicationRepository applicationRepository, 
                                 UserRepository userRepository, 
                                 AnimalRepository animalRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
    }

    // Başvuru Yap (POST)
    @PostMapping
    public Application submitApplication(@RequestParam UUID userId, 
                                         @RequestParam Long animalId, 
                                         @RequestBody String message) {
        
        Application application = new Application();
        
        application.setUserId(userId);
        application.setAnimalId(animalId);
        application.setNote(message);

        return applicationRepository.save(application);
    }

    // Kullanıcının Başvurularını Listele (GET)
    @GetMapping("/my")
    public List<Application> getMyApplications(@RequestParam UUID userId) {
        return applicationRepository.findByUserId(userId);
    }
}