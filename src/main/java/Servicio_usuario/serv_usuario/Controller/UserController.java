package Servicio_usuario.serv_usuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Servicio_usuario.serv_usuario.Model.User;
import Servicio_usuario.serv_usuario.Service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/apiFernando/v1.0/User")
public class UserController {
    @Autowired
    private UserService usuarioService;


    @GetMapping("/TraerTodos")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usuarioService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/TraerPorId/{id}")
    public ResponseEntity<User> getUserById(@PathVariable  Long id) {
        User user = usuarioService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/Crear")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = usuarioService.saveUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }
    @PutMapping("/Actualizar/{id}")
    public ResponseEntity<User> updateUser(@PathVariable  Long id, @RequestBody User user) {
        User updatedUser = usuarioService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/Eliminar/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable  Long id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
