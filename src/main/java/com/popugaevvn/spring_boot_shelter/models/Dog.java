package com.popugaevvn.spring_boot_shelter.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String name;
    @Column
    private byte age;
    @Column
    private String description;

    public Dog() {

    }

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
