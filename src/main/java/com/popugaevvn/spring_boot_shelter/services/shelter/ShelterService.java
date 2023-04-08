package com.popugaevvn.spring_boot_shelter.services.shelter;

import com.popugaevvn.spring_boot_shelter.api.request.shelter.ShelterRequest;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterMultiResponse;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterSingleResponse;
import com.popugaevvn.spring_boot_shelter.models.Dog;

import java.util.List;

public interface ShelterService {

    ShelterSingleResponse getShelterById(int id);

    List<ShelterMultiResponse> getListOfShelters();

    ShelterSingleResponse createNewShelter(ShelterRequest request);

    ShelterSingleResponse addDogInShelter(int id, Dog dog);

    ShelterSingleResponse updateShelter(int shelterId, ShelterRequest shelterUpdated);

    void deleteShelter(int shelterId);

}
