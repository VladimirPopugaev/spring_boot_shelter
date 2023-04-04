package com.popugaevvn.spring_boot_shelter.repository.dog;

import com.popugaevvn.spring_boot_shelter.models.Dog;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DogRepositoryMapImpl implements DogRepository {

    private static int DOG_COUNT;

    private final Map<Integer, Dog> dogList = new HashMap<>();

    @Override
    public List<Dog> index() {
        return dogList.values().stream().toList();
    }

    // TODO: add own type of error
    @Override
    public Dog getDogById(int id) {
        Dog dog = dogList.get(id);

        if (dog == null) {
            throw new IllegalArgumentException("Not found dog with id = " + id);
        }

        return dog;
    }

    @Override
    public void save(Dog dog) {
        dog.setId(++DOG_COUNT);
        dogList.put(dog.getId(), dog);
    }

    // TODO: add own type of error
    @Override
    public Dog updateDog(Dog updatedDog) {
        Dog notUpdatedDog = dogList.get(updatedDog.getId());

        if (notUpdatedDog == null) throw new IllegalArgumentException("Not found dog with id = " + updatedDog.getId());

        dogList.put(notUpdatedDog.getId(), updatedDog);

        return updatedDog;
    }

    @Override
    public List<Dog> getYoungerDog(byte maxAge) {
        return dogList.values().stream()
                .filter((dog) -> dog.getAge() < maxAge)
                .toList();
    }
}
