package com.popugaevvn.spring_boot_shelter.services.shelter;

import com.popugaevvn.spring_boot_shelter.api.request.shelter.ShelterRequest;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterMultiResponse;
import com.popugaevvn.spring_boot_shelter.api.response.shelter.ShelterSingleResponse;
import com.popugaevvn.spring_boot_shelter.exceptions.NotFoundEntityException;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.models.Shelter;
import com.popugaevvn.spring_boot_shelter.repository.shelter.ShelterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ShelterServiceImplTest {

    @Mock
    private ShelterRepository shelterRepository;

    @InjectMocks
    private ShelterServiceImpl shelterService;

    // Objects for testing
    private Shelter shelterTest;
    private ShelterRequest shelterRequestTest;

    @BeforeEach
    public void setup() {
        shelterRequestTest = new ShelterRequest();
        shelterRequestTest.setAddress("test address");
        shelterRequestTest.setPhoneNumber("+79001112233");
        shelterRequestTest.setOwnerFullName("Test owner");

        shelterTest = new Shelter("test address", "test phone", "Test owner");
    }

    /**
     * Function for returning list of shelters (mock for memory storing)
     *
     * @return - list with two shelters
     */
    private List<Shelter> getShelters() {
        Shelter shelter1 = new Shelter("first address", "first phone", "first owner");
        Shelter shelter2 = new Shelter("second address", "second phone", "second owner");

        return List.of(shelter1, shelter2);
    }


    @DisplayName("Success test save shelter")
    @Test
    void createNewShelter() {
        ShelterSingleResponse result = shelterService.createNewShelter(shelterRequestTest);

        Assertions.assertEquals(shelterRequestTest.getAddress(), result.getAddress());
        Assertions.assertEquals(shelterRequestTest.getPhoneNumber(), result.getPhoneNumber());
        Assertions.assertEquals(shelterRequestTest.getOwnerFullName(), result.getOwnerFullName());
        Assertions.assertArrayEquals(new Dog[]{}, result.getDogList().toArray());
    }

    @DisplayName("Success get shelter by id")
    @Test
    void getShelterByIdSuccess() {
        Mockito.when(shelterRepository.getShelterById(1)).thenReturn(shelterTest);
        ShelterSingleResponse result = shelterService.getShelterById(1);

        Assertions.assertEquals(shelterTest.getAddress(), result.getAddress());
        Assertions.assertEquals(shelterTest.getPhoneNumber(), result.getPhoneNumber());
        Assertions.assertEquals(shelterTest.getAddress(), result.getAddress());

    }

    @DisplayName("Error get shelter by id (null)")
    @Test
    void getShelterByIdError() {
        Mockito.when(shelterRepository.getShelterById(1)).thenThrow(NotFoundEntityException.class);

        Assertions.assertThrows(NotFoundEntityException.class, () -> shelterService.getShelterById(1));
    }

    @DisplayName("Success get list of shelters")
    @Test
    void getSheltersFromMemory() {
        List<Shelter> sheltersInMemory = getShelters();
        Mockito.when(shelterRepository.index()).thenReturn(sheltersInMemory);

        List<ShelterMultiResponse> results = shelterService.getListOfShelters();
        ShelterMultiResponse actualFirstShelter = results.get(0);
        Shelter expectedFirstShelter = sheltersInMemory.get(0);

        Assertions.assertEquals(expectedFirstShelter.getAddress(), actualFirstShelter.getAddress());
        Assertions.assertEquals(expectedFirstShelter.getPhoneNumber(), actualFirstShelter.getPhoneNumber());
        Assertions.assertEquals(expectedFirstShelter.getOwnerFullName(), actualFirstShelter.getOwnerFullName());
    }

    @DisplayName("Success add dog in shelter")
    @Test
    void addDogInShelterSuccess() {
        Dog dogForAdding = new Dog("Test name", (byte) 13, "Test description");
        shelterTest.addDog(dogForAdding);
        Mockito.when(shelterRepository.addDogInShelter(1, dogForAdding)).thenReturn(shelterTest);

        ShelterSingleResponse result = shelterService.addDogInShelter(1, dogForAdding);
        Dog actualDogInShelter = result.getDogList().get(0);

        // Check shelter
        Assertions.assertEquals(shelterTest.getAddress(), result.getAddress());
        Assertions.assertEquals(shelterTest.getOwnerFullName(), result.getOwnerFullName());
        Assertions.assertEquals(shelterTest.getPhoneNumber(), result.getPhoneNumber());

        // Check dog in shelter
        Assertions.assertEquals(dogForAdding.getName(), actualDogInShelter.getName());
        Assertions.assertEquals(dogForAdding.getAge(), actualDogInShelter.getAge());
        Assertions.assertEquals(dogForAdding.getDescription(), actualDogInShelter.getDescription());
    }

    @DisplayName("Error add dog in shelter (shelter not found)")
    @Test
    void addDogInShelterError() {
        Dog dogForAdding = new Dog("Test name", (byte) 13, "Test description");
        Mockito.when(
                shelterRepository.addDogInShelter(1, dogForAdding)
        ).thenThrow(new NotFoundEntityException("Not found"));

        final Exception exception = Assertions.assertThrows(
                NotFoundEntityException.class, () -> shelterService.addDogInShelter(1, dogForAdding)
        );

        Assertions.assertTrue(exception.getMessage().contains("Not found"));
    }
}