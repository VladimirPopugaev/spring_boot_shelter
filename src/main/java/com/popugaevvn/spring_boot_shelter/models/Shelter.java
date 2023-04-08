package com.popugaevvn.spring_boot_shelter.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shelter", orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    private List<Dog> dogs = new ArrayList<>();

    @Column
    private String phoneNumber;

    // TODO: implement user id (owner of shelter)
    private String ownerFullName;


    public Shelter() {

    }

    public Shelter(String address, String phoneNumber, String ownerFullName) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ownerFullName = ownerFullName;
    }

    /**
     * Additional method for database consistency. Add new dog in dog list.
     * @param dog - Dog class
     */
    public void addDog(Dog dog) {
        this.dogs.add(dog);
        dog.setShelter(this);
    }

    /**
     * Additional method for database consistency. Remove old dog from list.
     * @param dog - Dog class
     */
    public void removeDog(Dog dog) {
        dogs.remove(dog);
        dog.setShelter(null);
    }
}
