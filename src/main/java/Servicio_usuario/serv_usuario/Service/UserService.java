package Servicio_usuario.serv_usuario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicio_usuario.serv_usuario.Model.User;
import Servicio_usuario.serv_usuario.Repository.UserInterface;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

      @Autowired
      private UserInterface userInterface;

      public List<User> getAllUsers() {
            return userInterface.findAll();
      }

      public User getUserById(Long id) {
            return userInterface.findById(id).orElse(null);
      }
      public User saveUser(User user) {
            return userInterface.save(user);
      }
      public void deleteUser(Long id) {
            userInterface.deleteById(id);
      }
      public User updateUser(Long id, User user) {
            if (userInterface.existsById(id)) {
                  user.setId(id);
                  return userInterface.save(user);
            }
            return null;
      }

}
