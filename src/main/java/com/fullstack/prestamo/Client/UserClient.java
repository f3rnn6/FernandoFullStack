package com.fullstack.prestamo.Client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fullstack.prestamo.Client.DTO.userDTO;

import org.springframework.stereotype.Component;

@Component
public class UserClient {

    private final WebClient webClient;
    
    public UserClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("http://34.226.84.78:8089/apiFernando/v1.0/User/TraerPorId/{id}")
            .build();
    }
    public userDTO obtenerUsuarioPorId(Long id) {
        try {
            return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(userDTO.class)
                .block();
        } catch (WebClientResponseException.NotFound ex) {
            return null; // Usuario no encontrado
        }
    }
}
