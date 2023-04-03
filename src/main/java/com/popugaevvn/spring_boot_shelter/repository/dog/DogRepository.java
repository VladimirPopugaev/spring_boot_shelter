package com.popugaevvn.spring_boot_shelter.repository.dog;

import com.popugaevvn.spring_boot_shelter.models.Dog;

import java.util.List;

/**
 * Common interface for all storage repositories (storage methods)
 */
public interface DogRepository {

    List<Dog> index();

    Dog getDogById(int id);

    void save(Dog dog);

    Dog updateDog(Dog updatedDog);
}
