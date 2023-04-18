package com.popugaevvn.spring_boot_shelter.services.auth;

public interface AuthService {

    Boolean isAuthorize(String token, String cookie);

}
