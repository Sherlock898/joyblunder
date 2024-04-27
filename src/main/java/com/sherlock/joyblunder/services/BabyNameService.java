package com.sherlock.joyblunder.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sherlock.joyblunder.models.BabyName;
import com.sherlock.joyblunder.models.User;
import com.sherlock.joyblunder.repositories.BabyNameRepository;

import jakarta.validation.Valid;

@Service
public class BabyNameService {
    private final BabyNameRepository babyNameRepository;

    public BabyNameService(BabyNameRepository babyNameRepository) {
        this.babyNameRepository = babyNameRepository;
    }

    public List<BabyName> getAll() {
        return (List<BabyName>) babyNameRepository.findAll(); 
    }

    public void createBabyName(@Valid BabyName babyName) {
        babyNameRepository.save(babyName);
    }

    public BabyName findById(Long id) {
        return (BabyName) babyNameRepository.findById(id).orElse(null);
    }

    public void updateBabyName(BabyName babyNameToEdit) {
        babyNameRepository.save(babyNameToEdit);
    }

    public void deleteBabyName(Long id) {
        babyNameRepository.deleteById(id);
    }

    public void voteBabyName(BabyName babyNameToVote, User voter) {
        babyNameToVote.getVoters().add(voter);
        babyNameRepository.save(babyNameToVote);
    }

    public BabyName findByName(String name) {
        return babyNameRepository.findByName(name);
    }
    
}
