package com.mission3.controller;

import com.mission3.model.Pet;
import com.mission3.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/pet")
public class PetController {

    //Autowiring Pet repository
    @Autowired
    PetService petService;

    @GetMapping
    public List<Pet> getPet() {
        return petService.retrievePet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPet(@PathVariable Long id) {
        Optional<Pet> pet = petService.retrievePet(id);
        if (pet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(pet);
        }
    }

    @PostMapping
    public ResponseEntity<?> postPet(@Valid @RequestBody Pet body) {
        if (petService.createPet(body) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putPet(@PathVariable Long id, @RequestBody Pet body) {
        if (petService.updatePet(id, body) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(body);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {
        if (petService.deletePet(id) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(id);
        }
    }
}
