package com.popugaevvn.spring_boot_shelter.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Shelter {

    private int id;
    private String address;
    private List<Dog> dogs = new ArrayList<>();
    private String phoneNumber;

    // TODO: implement user id (owner of shelter)
    private String ownerFullName;

    public Shelter(String address, String phoneNumber, String ownerFullName) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ownerFullName = ownerFullName;
    }
}
