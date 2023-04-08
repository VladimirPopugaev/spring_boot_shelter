package com.popugaevvn.spring_boot_shelter.services.shelter;

import com.popugaevvn.spring_boot_shelter.api.request.shelter.ShelterRequest;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterMultiResponse;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterSingleResponse;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.models.Shelter;
import com.popugaevvn.spring_boot_shelter.repository.shelter.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    @Override
    public ShelterSingleResponse getShelterById(int id) {
        Shelter shelter = shelterRepository.getShelterById(id);
        return shelterToSingleResponse(shelter);
    }

    @Override
    public List<ShelterMultiResponse> getListOfShelters() {
        List<Shelter> shelters = shelterRepository.index();

        return shelters.stream().map(ShelterServiceImpl::shelterToMultiResponse).toList();
    }

    @Override
    public ShelterSingleResponse createNewShelter(ShelterRequest request) {
        Shelter newShelter = shelterRequestToShelter(request);
        shelterRepository.save(newShelter);

        return shelterToSingleResponse(newShelter);
    }

    @Override
    public ShelterSingleResponse addDogInShelter(int shelterId, Dog dog) {
        Shelter shelter = shelterRepository.addDogInShelter(shelterId, dog);

        return shelterToSingleResponse(shelter);
    }

    @Override
    public ShelterSingleResponse updateShelter(int shelterId, ShelterRequest infoForUpdateShelter) {
        Shelter shelterForUpdating = shelterRequestToShelter(infoForUpdateShelter);
        shelterForUpdating.setId(shelterId);

        Shelter updatedShelter = shelterRepository.updateShelter(shelterForUpdating);
        return shelterToSingleResponse(updatedShelter);
    }

    @Override
    public void deleteShelter(int shelterId) {
        shelterRepository.deleteShelter(shelterId);
    }

    private static ShelterSingleResponse shelterToSingleResponse(Shelter shelter) {
        return new ShelterSingleResponse(
                shelter.getId(),
                shelter.getAddress(),
                shelter.getPhoneNumber(),
                shelter.getDogs(),
                shelter.getOwnerFullName()
        );
    }

    private static ShelterMultiResponse shelterToMultiResponse(Shelter shelter) {
        return new ShelterMultiResponse(
                shelter.getId(),
                shelter.getAddress(),
                shelter.getPhoneNumber(),
                shelter.getOwnerFullName()
        );
    }

    private static Shelter shelterRequestToShelter(ShelterRequest request) {
        return new Shelter(request.getAddress(), request.getPhoneNumber(), request.getOwnerFullName());
    }
}
