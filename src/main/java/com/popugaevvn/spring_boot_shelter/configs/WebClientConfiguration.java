package com.popugaevvn.spring_boot_shelter.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * Creates an entity for accessing external services. Sets timeouts for all requests and adds default headers.
 */
@Configuration
public class WebClientConfiguration {

    private final static String BASE_URL = "http://localhost:4001/api";

    @Bean
    public WebClient getWebClientBuilder(WebClient.Builder webClientBuilder) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(2))
                        .addHandlerLast(new WriteTimeoutHandler(2))
                );

        return webClientBuilder.baseUrl(BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
