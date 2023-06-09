package com.popugaevvn.spring_boot_shelter.services.shelter;

import com.popugaevvn.spring_boot_shelter.api.request.shelter.ShelterRequest;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterMultiResponse;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterSingleResponse;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.models.Shelter;
import com.popugaevvn.spring_boot_shelter.repository.shelter.ShelterRepositoryHibernateAuto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private static final Logger LOGGER = LogManager.getLogger(ShelterServiceImpl.class);

    private final ShelterRepositoryHibernateAuto shelterRepository;

    @Override
    public ShelterSingleResponse getShelterById(int id) {
        Shelter shelter = shelterRepository.getReferenceById(id);
        LOGGER.info("Fetch shelter from DB with id: " + id);
        return shelterToSingleResponse(shelter);
    }

    @Override
    public List<ShelterMultiResponse> getListOfShelters() {
        List<Shelter> shelters = shelterRepository.findAll();

        return shelters.stream().map(ShelterServiceImpl::shelterToMultiResponse).toList();
    }

    @Override
    public ShelterSingleResponse createNewShelter(ShelterRequest request) {
        Shelter newShelter = shelterRequestToShelter(request);
        shelterRepository.save(newShelter);

        LOGGER.info("Dog was created. Info: " + newShelter);
        return shelterToSingleResponse(newShelter);
    }

    @Override
    @Transactional
    public ShelterSingleResponse addDogInShelter(int shelterId, Dog dog) {
        Shelter shelter = shelterRepository.getReferenceById(shelterId);
        shelter.addDog(dog);
        shelterRepository.save(shelter);
        LOGGER.info("In shelter with id = " + shelterId + " was added new dog. Dog info: " + dog);
        return shelterToSingleResponse(shelter);
    }

    @Override
    public ShelterSingleResponse updateShelter(int shelterId, ShelterRequest infoForUpdateShelter) {
        Shelter shelterForUpdating = shelterRequestToShelter(infoForUpdateShelter);
        shelterForUpdating.setId(shelterId);

        Shelter updatedShelter = shelterRepository.save(shelterForUpdating);
        LOGGER.info("Shelter with id = " + shelterId + " was updated.");
        return shelterToSingleResponse(updatedShelter);
    }

    @Override
    public void deleteShelter(int shelterId) {
        shelterRepository.deleteById(shelterId);
        LOGGER.info("Shelter with id = " + shelterId + " was deleted");
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
