package com.popugaevvn.spring_boot_shelter.services.auth;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthExternalService implements AuthService {

    private static final Logger LOGGER = LogManager.getLogger(AuthExternalService.class);

    private final WebClient webClient;

    @Override
    public Boolean isAuthorize(String token, String cookie) {
        LOGGER.info("External request to authorize service. Token: " + token);
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
