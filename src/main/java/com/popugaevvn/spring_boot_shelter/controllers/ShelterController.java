package com.popugaevvn.spring_boot_shelter.controllers;

import com.popugaevvn.spring_boot_shelter.api.request.dog.DogRequest;
import com.popugaevvn.spring_boot_shelter.api.request.shelter.ShelterRequest;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterMultiResponse;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterSingleResponse;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.services.shelter.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelters")
@RequiredArgsConstructor
public class ShelterController {

    private final ShelterService shelterService;

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

    @PutMapping("/{id}")
    public ShelterSingleResponse updateShelter(@PathVariable int id, @RequestBody ShelterRequest request) {
        return shelterService.updateShelter(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteShelter(@PathVariable int id) {
        shelterService.deleteShelter(id);

        return "Success";
    }

    @PostMapping("/{id}/dog")
    public ShelterSingleResponse addDogInShelter(@PathVariable int id, @RequestBody DogRequest dogRequest) {
        Dog addingDog = new Dog(dogRequest.getName(), dogRequest.getAge(), dogRequest.getDescription());
        return shelterService.addDogInShelter(id, addingDog);
    }
}
