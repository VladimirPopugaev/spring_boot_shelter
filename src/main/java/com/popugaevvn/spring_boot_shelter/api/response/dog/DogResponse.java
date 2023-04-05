package com.popugaevvn.spring_boot_shelter.api.response.dog;

import lombok.Data;

@Data
public final class DogResponse {
    private final int id;
    private final String name;
    private final String description;
    private final byte age;

}
