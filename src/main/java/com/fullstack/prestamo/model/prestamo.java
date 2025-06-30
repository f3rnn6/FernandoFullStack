package com.fullstack.prestamo.model;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Prestamo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String libro;

    @Column(nullable = false)
    private Long cantidadPaginas;

    @Column(nullable = false)
    private Date fechaInicio;

    @Column(nullable = false)
    private String estado; // "Activo", "Pagado", "Vencido", etc.
}
