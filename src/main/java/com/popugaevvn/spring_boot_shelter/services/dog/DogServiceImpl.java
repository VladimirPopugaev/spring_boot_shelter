package com.popugaevvn.spring_boot_shelter.services.dog;

import com.popugaevvn.spring_boot_shelter.api.request.dog.DogRequest;
import com.popugaevvn.spring_boot_shelter.api.response.dog.DogResponse;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.repository.dog.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository repository;

    @Override
    public DogResponse getDogById(int id) {
        Dog dog = repository.getDogById(id);

        return convertFromDog(dog);
    }

    @Override
    public Dog getDogAllInfo(int id) {
        return repository.getDogById(id);
    }

    @Override
    public List<DogResponse> getYoungerDogs(byte maxAge) {
        List<Dog> dogList = repository.getYoungerDog(maxAge);

        return dogList.stream().map(DogServiceImpl::convertFromDog).toList();
    }

    @Override
    public DogResponse createDog(DogRequest dogRequest) {
        Dog dog = convertToDog(dogRequest);
        repository.save(dog);

        return convertFromDog(dog);
    }

    /**
     * Cast class `Dog` in class `DogResponse`
     *
     * @param dog `Dog` class
     * @return new object of `DogResponse` class
     */
    private static DogResponse convertFromDog(Dog dog) {
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
