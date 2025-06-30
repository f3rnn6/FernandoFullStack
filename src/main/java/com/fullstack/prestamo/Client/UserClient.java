package com.fullstack.prestamo.Client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fullstack.prestamo.Client.DTO.userDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserClient {
    private final WebClient webClient;
    
    public UserClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("http://34.226.84.78:8080/apiFernando/v1.0/User")
            .build();
    }

    public boolean existeUsuario(Long id) {
        try {
            ResponseEntity<Void> response = webClient.get()
                .uri("/TraerPorId/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
            
            return response.getStatusCode().is2xxSuccessful();
        } catch (WebClientResponseException.NotFound ex) {
            return false;
        } catch (Exception ex) {
            throw new RuntimeException("Error al verificar usuario: " + ex.getMessage());
        }
    }
}