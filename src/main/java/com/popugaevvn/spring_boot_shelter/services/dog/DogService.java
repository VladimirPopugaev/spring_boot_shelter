package com.popugaevvn.spring_boot_shelter.services.dog;

import com.popugaevvn.spring_boot_shelter.api.request.DogRequest;
import com.popugaevvn.spring_boot_shelter.api.response.DogResponse;

import java.util.List;

public interface DogService {

    DogResponse getDogById(int id);

    List<DogResponse> getYoungerDogs(byte maxAge);

    DogResponse createDog(DogRequest dogRequest);

}
