package com.popugaevvn.spring_boot_shelter.models;

import lombok.Data;

@Data
public class Dog {

    private int id;
    private String name;
    private short weight;
    private short age;
    private String description;

}
