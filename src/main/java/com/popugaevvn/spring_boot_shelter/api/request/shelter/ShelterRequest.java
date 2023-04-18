package com.popugaevvn.spring_boot_shelter.api.request.shelter;

import lombok.Data;

@Data
public class ShelterRequest {

    private String address;
    private String phoneNumber;

    // TODO: implement user id (owner of shelter)
    private String ownerFullName;

    public ShelterRequest() {
    }
}
