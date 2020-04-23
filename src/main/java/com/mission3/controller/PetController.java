package com.mission3.controller;

import com.mission3.exception.ApiRequestException;
import com.mission3.model.PetResponse;
import com.mission3.model.Pet;
import com.mission3.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping
    public PetResponse getPet() {
            List<Pet> results = petService.retrievePet();
            PetResponse response = new PetResponse();
            response.setPets(results);
            return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPet(@PathVariable Long id) {
        Optional<Pet> pet = petService.retrievePet(id);
        if (pet.isEmpty()) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            throw new ApiRequestException("File is not on DB yet!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(pet);
        }
    }

    @PostMapping
    public ResponseEntity<?> postPet(@Valid @RequestBody Pet body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.createPet(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putPet(@PathVariable Long id, @RequestBody Pet body) {
        if (petService.updatePet(id, body)>0) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {
        if (petService.deletePet(id)>0) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
