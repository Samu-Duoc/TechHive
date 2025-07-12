package com.TechHive.Auth.Service;

import com.TechHive.Auth.Model.Auth;
import com.TechHive.Auth.Repository.AuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthRepository authRepository;

    private Auth auth;

    @BeforeEach
    void setUp() {
        auth = createTestAuth();
    }

    // Test para verificar el método findAll
    @Test
    public void testFindAll() {
        Auth auth = createTestAuth();
        when(authRepository.findAll()).thenReturn(List.of(auth));

        List<Auth> auths = authService.findAll();
        assertNotNull(auths);
        assertEquals(1, auths.size());
        assertEquals(auth.getId(), auths.get(0).getId());
    }

    // Test para verificar el método findById
    @Test
    public void testFindById() {
        Long id = 1L;
        Auth auth = createTestAuth();
        when(authRepository.findById(id)).thenReturn(Optional.of(auth));

        Auth found = authService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    // Test para verificar el método save
    @Test
    public void testSave() {
        Auth auth = createTestAuth();
        when(authRepository.save(auth)).thenReturn(auth);

        Auth saved = authService.save(auth);
        assertNotNull(saved);
        assertEquals(auth.getId(), saved.getId());
    }

    // Test para verificar el método deleteById
    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(authRepository).deleteById(id);

        authService.deleteById(id);
        verify(authRepository, times(1)).deleteById(id);
    }

    // Test para verificar creación de usuario cliente
    @Test
    public void testSaveClienteAuth() {
        Auth cliente = createTestCliente();
        when(authRepository.save(cliente)).thenReturn(cliente);

        Auth saved = authService.save(cliente);
        assertNotNull(saved);
        assertEquals("cliente", saved.getRol());
        assertEquals(cliente.getId(), saved.getId());
    }

    // Test para verificar creación de usuario admin
    @Test
    public void testSaveAdminAuth() {
        Auth admin = createTestAdmin();
        when(authRepository.save(admin)).thenReturn(admin);

        Auth saved = authService.save(admin);
        assertNotNull(saved);
        assertEquals("admin", saved.getRol());
        assertEquals(admin.getId(), saved.getId());
    }

    // Test para verificar creación de usuario vendedor
    @Test
    public void testSaveVendedorAuth() {
        Auth vendedor = createTestVendedor();
        when(authRepository.save(vendedor)).thenReturn(vendedor);

        Auth saved = authService.save(vendedor);
        assertNotNull(saved);
        assertEquals("vendedor", saved.getRol());
        assertEquals(vendedor.getId(), saved.getId());
    }

    // Test para verificar el método update
    @Test
    public void testUpdate() {
        Auth auth = createTestCliente();
        auth.setCorreo("juan.actualizado@mail.com");
        when(authRepository.save(auth)).thenReturn(auth);

        Auth updated = authService.save(auth);
        assertNotNull(updated);
        assertEquals("juan.actualizado@mail.com", updated.getCorreo());
        assertEquals(auth.getId(), updated.getId());
    }

    // Métodos auxiliares para crear auth de prueba por rol

    private Auth createTestAuth() {
        return createTestCliente(); // Método principal usa cliente por defecto
    }

    // Método para crear un auth cliente de prueba
    private Auth createTestCliente() {
        Auth auth = new Auth();
        auth.setId(1L);
        auth.setCorreo("juan.perez@gmail.com");
        auth.setPassword("password123");
        auth.setRol("cliente");
        return auth;
    }

    // Método para crear un auth admin de prueba
    private Auth createTestAdmin() {
        Auth auth = new Auth();
        auth.setId(2L);
        auth.setCorreo("admin@techhive.com");
        auth.setPassword("admin123");
        auth.setRol("admin");
        return auth;
    }

    // Método para crear un auth vendedor de prueba
    private Auth createTestVendedor() {
        Auth auth = new Auth();
        auth.setId(3L);
        auth.setCorreo("vendedor@techhive.com");
        auth.setPassword("vendedor123");
        auth.setRol("vendedor");
        return auth;
    }
}
