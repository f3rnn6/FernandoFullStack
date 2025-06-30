package com.fullstack.prestamo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.prestamo.model.prestamo;
import com.fullstack.prestamo.service.prestamoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/prestamos")
public class prestamoController {

    @Autowired
    private prestamoService prestamoService;

    @GetMapping("/prestamosPorId")
    public List<prestamo> obtenerPrestamosPorId(@RequestParam Long id) {
        return prestamoService.obtenerPrestamosPorId(id);
    }
 @PostMapping("/agregarPrestamo")
    public ResponseEntity<?> agregarPrestamo(@RequestBody prestamo newPrestamo) {
        try {
            prestamo prestamoCreado = prestamoService.agregarPrestamo(newPrestamo);
            return ResponseEntity.status(HttpStatus.CREATED).body(prestamoCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }
    @DeleteMapping("/eliminarPrestamo")
    public void eliminarPrestamo(@RequestParam Long id) {
        prestamoService.eliminarPrestamo(id);
    }
    
}
