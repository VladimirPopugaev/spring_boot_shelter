package com.popugaevvn.spring_boot_shelter.services.dog;

import com.popugaevvn.spring_boot_shelter.api.request.dog.DogRequest;
import com.popugaevvn.spring_boot_shelter.api.response.dog.DogResponse;
import com.popugaevvn.spring_boot_shelter.models.Dog;

import java.util.List;

public interface DogService {

    DogResponse getDogById(int id);

    Dog getDogAllInfo(int id);

    List<DogResponse> getYoungerDogs(byte maxAge);

    DogResponse createDog(DogRequest dogRequest);

}
