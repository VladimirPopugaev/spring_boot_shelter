package com.popugaevvn.spring_boot_shelter.repository.shelter;

import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.models.Shelter;

import java.util.List;

public interface ShelterRepository {

    List<Shelter> index();

    Shelter getShelterById(int id);

    void save(Shelter shelter);

    Shelter updateShelter(Shelter updatedShelter);

    List<Dog> getDogsFromShelter(int shelterId);

    Shelter addDogInShelter(int shelterId, Dog dog);

}
