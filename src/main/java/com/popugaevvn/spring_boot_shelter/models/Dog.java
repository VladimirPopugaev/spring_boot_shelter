package com.popugaevvn.spring_boot_shelter.models;

import lombok.Data;

@Data
public class Dog {

    private int id;
    private String name;
    private byte age;
    private String description;

    public Dog(String name, byte age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    // Can be null
    private short weight;

    // Can be null
    private String diseases;

}
