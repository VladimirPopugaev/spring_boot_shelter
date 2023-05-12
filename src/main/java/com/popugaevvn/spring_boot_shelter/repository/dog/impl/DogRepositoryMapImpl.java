package com.popugaevvn.spring_boot_shelter.repository.dog.impl;

import com.popugaevvn.spring_boot_shelter.api.exceptions.NotFoundEntityException;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.models.Shelter;
import com.popugaevvn.spring_boot_shelter.repository.dog.DogRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class DogRepositoryMapImpl implements DogRepository {

    private static int DOG_COUNT;


    private final Shelter shelterForList = new Shelter("ул. Mock, д. Заглушки", "8 800 555 35 35", "OOO GOOD BOY HOME");
    private final Map<Integer, Dog> dogList = new HashMap<>();

    {
        dogList.put(++DOG_COUNT, new Dog("Mark", (byte) 5, "The best dog in the world", shelterForList));
        dogList.put(++DOG_COUNT, new Dog("Ray", (byte) 12, "The second best dog in the world", shelterForList));
        dogList.put(++DOG_COUNT, new Dog("Banny", (byte) 5, "The thrid best dog in the world", shelterForList));
    }

    @Override
    public List<Dog> index() {
        return dogList.values().stream().toList();
    }

    @Override
    public Dog getDogById(int id) {
        Dog dog = dogList.get(id);

        if (dog == null) {
            throw new NotFoundEntityException("Not found dog with id = " + id);
        }

        return dog;
    }

    @Override
    // TODO: create checking on existing of dog in memory
    public void save(Dog dog) {
        dog.setId(++DOG_COUNT);
        dog.setShelter(shelterForList);
        dogList.put(dog.getId(), dog);
    }

    @Override
    public Dog updateDog(Dog updatedDog) {
        Dog notUpdatedDog = dogList.get(updatedDog.getId());

        if (notUpdatedDog == null) throw new NotFoundEntityException("Not found dog with id = " + updatedDog.getId());

        dogList.put(notUpdatedDog.getId(), updatedDog);

        return updatedDog;
    }

    @Override
    public List<Dog> getYoungerDog(byte maxAge) {
        return dogList.values().stream()
                .filter((dog) -> dog.getAge() < maxAge)
                .toList();
    }

    @Override
    public void deleteDog(int dogId) {
        dogList.remove(dogId);
    }
}
