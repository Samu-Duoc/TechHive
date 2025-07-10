package com.TechHive.Auth.Service;

import com.TechHive.Auth.Model.Auth;
import com.TechHive.Auth.Repository.AuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test") 
public class AuthServiceTest {

    @Mock
    private AuthRepository authRepository;  // Mock del repositorio

    @InjectMocks
    private AuthService authService;  // Servicio a probar

    private Auth auth;  // Objeto Auth para las pruebas

    @BeforeEach
    void setUp() {
        // Crea un objeto Auth con datos fijos para las pruebas
        auth = new Auth();
        auth.setId(1L);
        auth.setCorreo("john.doe@mail.com");
        auth.setPassword("password123");
        auth.setRol("CLIENTE");
    }

    @Test
    void testFindById() {
        // Simula que el repositorio devuelve el usuario cuando se busca por id
        when(authRepository.findById(1L)).thenReturn(Optional.of(auth));

        // Llama al método del servicio
        Auth foundAuth = authService.findById(1L);

        // Verifica que el resultado sea el esperado
        assertEquals(auth, foundAuth);
    }

    @Test
    void testSave() {
        // Simula que el repositorio guarda un usuario
        when(authRepository.save(auth)).thenReturn(auth);

        // Llama al método del servicio
        Auth savedAuth = authService.save(auth);

        // Verifica que el resultado sea el esperado
        assertEquals(auth, savedAuth);
    }

    @Test
    void testDeleteById() {
        // Simula que el repositorio encuentra el usuario por id
        when(authRepository.findById(1L)).thenReturn(Optional.of(auth));

        // Llama al método del servicio
        authService.deleteById(1L);

        // Verifica que el repositorio haya sido llamado para eliminar al usuario
        verify(authRepository, times(1)).deleteById(1L); // Cambiado para usar deleteById
    }
}
