package com.mission3.model;

import com.mission3.model.Pet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PetResponse {
    private List<Pet> pets;
}
