package com.fullstack.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.prestamo.Client.UserClient;
import com.fullstack.prestamo.model.prestamo;
import com.fullstack.prestamo.repository.prestamoInterface;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class prestamoService {

    @Autowired
    private prestamoInterface prestamoRepo;
    @Autowired
    private UserClient userClient;

    public List<prestamo> obtenerPrestamosPorId(Long id) {
        return prestamoRepo.findAll().stream()
                .filter(prestamo -> prestamo.getUserId().equals(id))
                .toList();
    }

   public prestamo agregarPrestamo(prestamo newPrestamo) {
        // Validación básica
        if (newPrestamo.getUserId() == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido");
        }

        // Verifica existencia del usuario
        if (!userClient.existeUsuario(newPrestamo.getUserId())) {
            throw new RuntimeException("No existe usuario con ID: " + newPrestamo.getUserId());
        }

        // Guarda el préstamo
        return prestamoRepo.save(newPrestamo);
    }
    public void eliminarPrestamo(Long id) {
        prestamoRepo.deleteById(id);
    }
    public prestamo actualizarPrestamo(prestamo updatedPrestamo) {
        if (prestamoRepo.existsById(updatedPrestamo.getId())) {
            return prestamoRepo.save(updatedPrestamo);
        } else {
            throw new RuntimeException("Prestamo no encontrado");
        }
    }
}
