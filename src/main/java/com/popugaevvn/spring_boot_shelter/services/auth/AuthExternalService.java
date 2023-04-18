package com.popugaevvn.spring_boot_shelter.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthExternalService implements AuthService {

    private final WebClient webClient;

    @Override
    public Boolean isAuthorize(String token, String cookie) {
        return this.webClient
                .get()
                .uri("/accounts/check_auth")
                .header("Authorization", token)
                .cookie("_auth_service_key", cookie)
                .exchangeToMono(authResponse -> {
                    if (authResponse.statusCode().is2xxSuccessful()) {
                        return Mono.just(true);
                    } else return Mono.just(false);
                })
                .block();
    }
}
