package com.mission3.service;

import com.mission3.model.Pet;
import com.mission3.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public List<Pet> retrievePet() {
        return petRepository.selectAll();
    }

    public Pet retrievePet(Long id) {
        return petRepository.select(id);
    }

    public Pet createPet(Pet pet) {
        return petRepository.insert(pet);
    }

    public int updatePet(Long id, Pet pet) {
        return petRepository.update(id, pet);
    }

    public int deletePet(Long id) {
        return petRepository.delete(id);
    }
}
