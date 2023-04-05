package com.popugaevvn.spring_boot_shelter.controllers;

import com.popugaevvn.spring_boot_shelter.api.request.dog.DogRequest;
import com.popugaevvn.spring_boot_shelter.api.request.shelter.ShelterRequest;
import com.popugaevvn.spring_boot_shelter.api.response.dog.DogResponse;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterMultiResponse;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterSingleResponse;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.services.dog.DogService;
import com.popugaevvn.spring_boot_shelter.services.shelter.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelters")
@RequiredArgsConstructor
public class ShelterController {

    private final ShelterService shelterService;
    private final DogService dogService;

    @GetMapping()
    public List<ShelterMultiResponse> getAllShelters() {
        return shelterService.getListOfShelters();
    }

    @GetMapping("/{id}")
    public ShelterSingleResponse getShelterById(@PathVariable int id) {
        return shelterService.getShelterById(id);
    }

    @PostMapping()
    public ShelterSingleResponse createShelter(@RequestBody ShelterRequest request) {
        return shelterService.createNewShelter(request);
    }

    @PostMapping("/{id}/dog")
    public ShelterSingleResponse addDogInShelter(@PathVariable int id, @RequestBody DogRequest dog) {
        DogResponse response = dogService.createDog(dog);
        Dog addingDog = dogService.getDogAllInfo(response.getId());
        return shelterService.addDogInShelter(id, addingDog);
    }
}
