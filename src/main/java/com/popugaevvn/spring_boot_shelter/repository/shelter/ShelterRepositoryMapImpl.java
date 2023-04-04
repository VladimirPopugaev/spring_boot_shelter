package com.popugaevvn.spring_boot_shelter.repository.shelter;

import com.popugaevvn.spring_boot_shelter.exceptions.NotFoundEntityException;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.models.Shelter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShelterRepositoryMapImpl implements ShelterRepository {

    private final Map<Integer, Shelter> shelterList = new HashMap<>();

    @Override
    public List<Shelter> index() {
        return shelterList.values().stream().toList();
    }

    @Override
    public Shelter getShelterById(int id) {
        Shelter shelter = shelterList.get(id);

        if (shelter == null) {
            throw new NotFoundEntityException("Not found shelter with id = " + id);
        }

        return shelter;
    }

    @Override
    public void save(Shelter shelter) {
        shelterList.put(shelter.getId(), shelter);
    }

    @Override
    public Shelter updateShelter(Shelter updatedShelter) {
        Shelter notUpdatedShelter = shelterList.get(updatedShelter.getId());

        if (notUpdatedShelter == null) throw new NotFoundEntityException("Not found shelter with id = " + updatedShelter.getId());

        shelterList.put(notUpdatedShelter.getId(), updatedShelter);

        return updatedShelter;
    }

    @Override
    public List<Dog> getDogsFromShelter(int shelterId) {
        Shelter shelter = shelterList.get(shelterId);

        if (shelter == null) throw new NotFoundEntityException("Not found shelter with id = " + shelterId);

        return shelter.getDogs();
    }
}
