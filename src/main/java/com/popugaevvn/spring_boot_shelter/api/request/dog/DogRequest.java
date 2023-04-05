package com.popugaevvn.spring_boot_shelter.api.request.dog;

import lombok.Data;

@Data
public class DogRequest {

    private String name;
    private String description;
    private byte age;

}
