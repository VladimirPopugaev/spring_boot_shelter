package com.popugaevvn.spring_boot_shelter.api.request.shelter;

import lombok.Data;

@Data
public class ShelterRequest {

    private final String address;
    private final String phoneNumber;

    // TODO: implement user id (owner of shelter)
    private final String ownerFullName;

}
