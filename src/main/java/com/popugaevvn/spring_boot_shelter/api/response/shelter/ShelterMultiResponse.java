package com.popugaevvn.spring_boot_shelter.api.response.shelter;

import lombok.Data;

@Data
public class ShelterMultiResponse {

    private final int id;
    private final String address;
    private final String phoneNumber;

    // TODO: implement user id (owner of shelter)
    private final String ownerFullName;

}
