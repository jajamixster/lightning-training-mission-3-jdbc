package com.mission3.controller;

import com.mission3.exception.IdNotFoundException;
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
    public ResponseEntity<?> getPet(@PathVariable Long id) throws IdNotFoundException {
        Pet pet = petService.retrievePet(id);
        if(pet == null)
            throw new IdNotFoundException("Id '" + id + "' not found in Pet repository - GET Method");
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }

    @PostMapping
    public ResponseEntity<?> postPet(@Valid @RequestBody Pet body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.createPet(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putPet(@PathVariable Long id, @Valid @RequestBody Pet body) throws IdNotFoundException {
        // 0 = no line updated
        int foundPet = petService.updatePet(id, body);
        if(foundPet == 0)
            throw new IdNotFoundException("Id '" + id + "' not found in Pet repository - PUT Method");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //TODO custom error meassge handling
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) throws IdNotFoundException{
        // 0 = no line updated
        int foundPet = petService.deletePet(id);
        if(foundPet == 0)
            throw new IdNotFoundException("Id '" + id + "'not found in Pet repository - DELETE Method");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
