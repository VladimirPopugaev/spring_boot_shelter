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
}
