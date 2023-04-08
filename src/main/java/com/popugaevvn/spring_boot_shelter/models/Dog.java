package com.popugaevvn.spring_boot_shelter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Shelter shelter;

    @Column
    private short weight;

    @Column
    private String diseases;

    public Dog() {

    }

    public Dog(String name, byte age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    public Dog(String name, byte age, String description, Shelter shelter) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.shelter = shelter;
    }



}
