package com.popugaevvn.spring_boot_shelter.api.response.shelter;

import com.popugaevvn.spring_boot_shelter.models.Dog;
import lombok.Data;

import java.util.List;

@Data
public class ShelterSingleResponse {

    private final String address;
    private final String phoneNumber;
    private final List<Dog> dogList;

    // TODO: implement user id (owner of shelter)
    private final String ownerFullName;

}
