package Servicio_usuario.serv_usuario.Model;



import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name= "User")  
@Data  
@NoArgsConstructor  
@AllArgsConstructor  
public class User {


    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;


    @Column(unique=true, length = 13, nullable=false)  
    private String run;

    @Column(nullable=false)  
    private String nombres;
    @Column(nullable=false)  
    private String password;
    @Column(nullable=false)  
    private String username;
    @Column(nullable=false)  
    private String apellidos;

    @Column(nullable=true) 
    private Date fechaNacimiento;

    @Column(nullable=false) 
    private String correo;




}
