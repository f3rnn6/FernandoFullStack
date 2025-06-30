package com.fullstack.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.prestamo.model.prestamo;

public interface prestamoInterface extends JpaRepository<prestamo, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
