package com.fullstack.prestamo.Client.DTO;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDTO {
    private Long id;
    private String run;
    private String nombres;
    private String password;
    private String username;
    private String apellidos;
    private Date fechaNacimiento;
    private String correo;

}
