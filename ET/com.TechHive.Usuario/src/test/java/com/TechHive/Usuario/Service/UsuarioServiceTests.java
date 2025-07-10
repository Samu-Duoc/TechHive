package com.TechHive.Usuario.Service;

import com.TechHive.Usuario.Model.Usuarios;
import com.TechHive.Usuario.Repository.UsuarioRepository;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {

    @InjectMocks
    private UsuariosService usuariosService;

    @Mock
    private UsuarioRepository usuarioRepository;


    // Test para verificar el método findAll
    @Test
    public void testFindAll() {
        Usuarios usuario = createTestUsuario();
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuarios> usuarios = usuariosService.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals(usuario.getId(), usuarios.get(0).getId());
    }

    // Test para verificar el método findById
    @Test
    public void testFindById() {
        Long id = 1L;
        Usuarios usuario = createTestUsuario();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuarios found = usuariosService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    // Test para verificar el método save
    @Test
    public void testSave() {
        Usuarios usuario = createTestUsuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuarios saved = usuariosService.save(usuario);
        assertNotNull(saved);
        assertEquals(usuario.getId(), saved.getId());
    }

    // Test para verificar el método delete
    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(usuarioRepository).deleteById(id);

        usuariosService.deleteById(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    // Test para verificar creación de usuario cliente
    @Test
    public void testSaveClienteUser() {
        Usuarios cliente = createTestCliente();
        when(usuarioRepository.save(cliente)).thenReturn(cliente);

        Usuarios saved = usuariosService.save(cliente);
        assertNotNull(saved);
        assertEquals("cliente", saved.getRol());
        assertEquals(cliente.getId(), saved.getId());
    }

    // Test para verificar creación de usuario admin
    @Test
    public void testSaveAdminUser() {
        Usuarios admin = createTestAdmin();
        when(usuarioRepository.save(admin)).thenReturn(admin);

        Usuarios saved = usuariosService.save(admin);
        assertNotNull(saved);
        assertEquals("admin", saved.getRol());
        assertEquals(admin.getId(), saved.getId());
    }

    // Test para verificar creación de usuario vendedor
    @Test
    public void testSaveVendedorUser() {
        Usuarios vendedor = createTestVendedor();
        when(usuarioRepository.save(vendedor)).thenReturn(vendedor);

        Usuarios saved = usuariosService.save(vendedor);
        assertNotNull(saved);
        assertEquals("vendedor", saved.getRol());
        assertEquals(vendedor.getId(), saved.getId());
    }

    // Test para verificar el método update
    @Test
    public void testUpdate() {
        Usuarios usuario = createTestCliente();
        usuario.setNombres("Juan Actualizado");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuarios updated = usuariosService.save(usuario);
        assertNotNull(updated);
        assertEquals("Juan Actualizado", updated.getNombres());
        assertEquals(usuario.getId(), updated.getId());
    }

    // Test para verificar el método deleteById
    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(usuarioRepository).deleteById(id);

        usuariosService.deleteById(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    // Métodos auxiliares para crear usuarios de prueba por rol

    private Usuarios createTestUsuario() {
        return createTestCliente(); // Método principal usa cliente por defecto
    }

    // Método para crear un usuario cliente de prueba
    private Usuarios createTestCliente() {
        Usuarios usuario = new Usuarios();
        usuario.setId(1L);
        usuario.setRut("12345678-9");
        usuario.setNombres("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan.perez@gmail.com");
        usuario.setPassword("password123");
        usuario.setTelefono("123456789");
        usuario.setDireccion("Calle Falsa 123");
        usuario.setRol("cliente");
        return usuario;
    }

    //Metodos para crear administrador
    private Usuarios createTestAdmin() {
        Usuarios usuario = new Usuarios();
        usuario.setId(2L);
        usuario.setRut("98765432-1");
        usuario.setNombres("María");
        usuario.setApellidos("González");
        usuario.setCorreo("maria.admin@techhive.com");
        usuario.setPassword("admin2024");
        usuario.setTelefono("987654321");
        usuario.setDireccion("Av. Administración 456");
        usuario.setRol("admin");
        return usuario;
    }

    // Métodos para crear un usuario vendedor de prueba
    private Usuarios createTestVendedor() {
        Usuarios usuario = new Usuarios();
        usuario.setId(3L);
        usuario.setRut("11111111-1");
        usuario.setNombres("Carlos");
        usuario.setApellidos("López");
        usuario.setCorreo("carlos.vendedor@techhive.com");
        usuario.setPassword("venta2024");
        usuario.setTelefono("555123456");
        usuario.setDireccion("Plaza Ventas 789");
        usuario.setRol("vendedor");
        return usuario;
    }
}
