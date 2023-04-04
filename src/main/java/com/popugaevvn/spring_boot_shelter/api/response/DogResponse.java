package com.popugaevvn.spring_boot_shelter.api.response;

import lombok.Data;

@Data
public final class DogResponse {

    private final String name;
    private final String description;
    private final byte age;

}
