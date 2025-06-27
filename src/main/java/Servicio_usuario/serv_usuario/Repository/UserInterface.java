package Servicio_usuario.serv_usuario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Servicio_usuario.serv_usuario.Model.User;

public interface UserInterface extends JpaRepository<User, Long>  {

}
