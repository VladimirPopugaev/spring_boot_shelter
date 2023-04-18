package com.popugaevvn.spring_boot_shelter.controllers;

import com.popugaevvn.spring_boot_shelter.api.request.dog.DogRequest;
import com.popugaevvn.spring_boot_shelter.api.response.dog.DogResponse;
import com.popugaevvn.spring_boot_shelter.services.dog.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @GetMapping("/list/{maxAge}")
    public List<DogResponse> getYoungerDogs(@PathVariable byte maxAge) {
        return dogService.getYoungerDogs(maxAge);
    }

    @GetMapping("/{id}")
    public DogResponse getDogById(@PathVariable int id) {
        return dogService.getDogById(id);
    }

    //@PostMapping()
    public DogResponse createDog(@RequestBody DogRequest request) {
        return dogService.createDog(request);
    }

    @PutMapping("/{id}")
    public DogResponse updateDog(@PathVariable int id, @RequestBody DogRequest request) {
        return dogService.updateDog(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteDog(@PathVariable int id) {
        dogService.deleteDog(id);

        return "Success!";
    }
}
