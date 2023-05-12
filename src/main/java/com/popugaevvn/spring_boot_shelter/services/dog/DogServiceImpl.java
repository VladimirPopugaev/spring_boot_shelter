package com.popugaevvn.spring_boot_shelter.services.dog;

import com.popugaevvn.spring_boot_shelter.api.request.dog.DogRequest;
import com.popugaevvn.spring_boot_shelter.api.response.dog.DogResponse;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.repository.dog.DogRepositoryHibernateAuto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private static final Logger LOGGER = LogManager.getLogger(DogServiceImpl.class);

    private final DogRepositoryHibernateAuto repository;

    @Override
    public DogResponse getDogById(int id) {
        Dog dog = repository.getReferenceById(id);
        LOGGER.info("Fetch dog from DB with id: " + id);
        return convertFromDogToResponse(dog);
    }

    @Override
    public Dog getDogAllInfo(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<DogResponse> getYoungerDogs(byte maxAge) {
        List<Dog> dogList = repository.findDogsByAgeLessThan(maxAge);

        return dogList.stream().map(DogServiceImpl::convertFromDogToResponse).toList();
    }


    /**
     * @deptecated
     * This method of addition makes no sense since the dog is always connected to the shelter
     * That's why the action of adding is done from the shelter service.
     */
    @Deprecated
    @Override
    public DogResponse createDog(DogRequest dogRequest) {
        Dog dog = convertToDog(dogRequest);
        repository.save(dog);
        LOGGER.info("Dog was created. Info: " + dog);
        return convertFromDogToResponse(dog);
    }

    @Override
    public DogResponse updateDog(int dogId, DogRequest newInfoAboutDog) {
        Dog unupdatedDog = repository.getReferenceById(dogId);
        Dog dogForUpdate = convertToDog(newInfoAboutDog);
        dogForUpdate.setId(unupdatedDog.getId());
        dogForUpdate.setShelter(unupdatedDog.getShelter());

        Dog updatedDog = repository.save(dogForUpdate);
        LOGGER.info("Dog was updated. Updated dog id: " + dogId);
        return convertFromDogToResponse(updatedDog);
    }

    @Override
    public void deleteDog(int dogId) {
        repository.deleteById(dogId);
        LOGGER.info("Dog wtih id = " + dogId + " was deleted");
    }

    /**
     * Cast class `Dog` in class `DogResponse`
     *
     * @param dog `Dog` class
     * @return new object of `DogResponse` class
     */
    private static DogResponse convertFromDogToResponse(Dog dog) {
        return new DogResponse(dog.getId(), dog.getName(), dog.getDescription(), dog.getAge());
    }

    /**
     * Cast class `DogRequest` in class `Dog`
     * @param dogRequest `DogRequest` class
     * @return new object of `Dog` class
     */
    private static Dog convertToDog(DogRequest dogRequest) {
        return new Dog(dogRequest.getName(), dogRequest.getAge(), dogRequest.getDescription());
    }
}
