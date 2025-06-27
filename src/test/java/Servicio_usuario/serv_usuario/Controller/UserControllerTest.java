package Servicio_usuario.serv_usuario.Controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Servicio_usuario.serv_usuario.Model.User;
import Servicio_usuario.serv_usuario.Repository.UserInterface;
import Servicio_usuario.serv_usuario.Service.UserService;
public class UserControllerTest {
@Mock
    private UserInterface userInterface;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        user = new User();
        user.setId(1L);
        user.setRun("12345678-9");
        user.setNombres("Test");
        user.setApellidos("User");
        user.setCorreo("test@example.com");
        user.setUsername("testuser");
        user.setPassword("password");
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        when(userInterface.findAll()).thenReturn(Arrays.asList(user));
        
        // Act
        List<User> result = userService.getAllUsers();
        
        // Assert
        assertEquals(1, result.size());
        verify(userInterface, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        // Arrange
        when(userInterface.findById(1L)).thenReturn(Optional.of(user));
        
        // Act
        User result = userService.getUserById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals("Test", result.getNombres());
        verify(userInterface, times(1)).findById(1L);
    }

    @Test
    void testGetUserById_NotFound() {
        // Arrange
        when(userInterface.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        User result = userService.getUserById(1L);
        
        // Assert
        assertNull(result);
        verify(userInterface, times(1)).findById(1L);
    }

    @Test
    void testSaveUser() {
        // Arrange
        when(userInterface.save(user)).thenReturn(user);
        
        // Act
        User result = userService.saveUser(user);
        
        // Assert
        assertNotNull(result);
        verify(userInterface, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        doNothing().when(userInterface).deleteById(1L);
        
        // Act
        userService.deleteUser(1L);
        
        // Assert
        verify(userInterface, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        when(userInterface.existsById(1L)).thenReturn(true);
        when(userInterface.save(user)).thenReturn(user);
        
        // Act
        User result = userService.updateUser(1L, user);
        
        // Assert
        assertNotNull(result);
        verify(userInterface, times(1)).existsById(1L);
        verify(userInterface, times(1)).save(user);
    }

    @Test
    void testUpdateUser_NotFound() {
        // Arrange
        when(userInterface.existsById(1L)).thenReturn(false);
        
        // Act
        User result = userService.updateUser(1L, user);
        
        // Assert
        assertNull(result);
        verify(userInterface, times(1)).existsById(1L);
        verify(userInterface, never()).save(user);
    }
}
