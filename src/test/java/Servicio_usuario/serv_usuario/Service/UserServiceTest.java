package Servicio_usuario.serv_usuario.Service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import Servicio_usuario.serv_usuario.Controller.UserController;
import Servicio_usuario.serv_usuario.Model.User;

public class UserServiceTest {

    private MockMvc mockMvc;

    @Mock
    private UserService usuarioService;

    @InjectMocks
    private UserController userController;

    private User user;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
        
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
    void testGetAllUsers() throws Exception {
        // Arrange
        List<User> users = Arrays.asList(user);
        when(usuarioService.getAllUsers()).thenReturn(users);
        
        // Act & Assert
        mockMvc.perform(get("/apiFernando/v1.0/User/TraerTodos"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].nombres").value("Test"));
        
        verify(usuarioService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() throws Exception {
        // Arrange
        when(usuarioService.getUserById(1L)).thenReturn(user);
        
        // Act & Assert
        mockMvc.perform(get("/apiFernando/v1.0/User/TraerPorId/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.nombres").value("Test"));
        
        verify(usuarioService, times(1)).getUserById(1L);
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        // Arrange
        when(usuarioService.getUserById(1L)).thenReturn(null);
        
        // Act & Assert
        mockMvc.perform(get("/apiFernando/v1.0/User/TraerPorId/1"))
               .andExpect(status().isNotFound());
        
        verify(usuarioService, times(1)).getUserById(1L);
    }

    @Test
    void testCreateUser() throws Exception {
        // Arrange
        when(usuarioService.saveUser(any(User.class))).thenReturn(user);
        
        // Act & Assert
        mockMvc.perform(post("/apiFernando/v1.0/User/Crear")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(user)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.nombres").value("Test"));
        
        verify(usuarioService, times(1)).saveUser(any(User.class));
    }

    @Test
    void testUpdateUser() throws Exception {
        // Arrange
        when(usuarioService.updateUser(eq(1L), any(User.class))).thenReturn(user);
        
        // Act & Assert
        mockMvc.perform(put("/apiFernando/v1.0/User/Actualizar/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(user)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.nombres").value("Test"));
        
        verify(usuarioService, times(1)).updateUser(eq(1L), any(User.class));
    }

    @Test
    void testUpdateUser_NotFound() throws Exception {
        // Arrange
        when(usuarioService.updateUser(eq(1L), any(User.class))).thenReturn(null);
        
        // Act & Assert
        mockMvc.perform(put("/apiFernando/v1.0/User/Actualizar/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(user)))
               .andExpect(status().isNotFound());
        
        verify(usuarioService, times(1)).updateUser(eq(1L), any(User.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        // Arrange
        doNothing().when(usuarioService).deleteUser(1L);
        
        // Act & Assert
        mockMvc.perform(delete("/apiFernando/v1.0/User/Eliminar/1"))
               .andExpect(status().isNoContent());
        
        verify(usuarioService, times(1)).deleteUser(1L);
    }
}
