package com.TechHive.Usuario.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.TechHive.Usuario.Model.Usuarios;
import com.TechHive.Usuario.Service.UsuariosService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(UsuarioController.class) // Asegúrate de que el controlador esté correctamente importado
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc para simular peticiones HTTP    @MockBean
    private UsuariosService usuariosService; // Servicio a probar, simulado con Mockito

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos a JSON

    private Usuarios usuario; // Objeto Usuario para las pruebas

    @BeforeEach
    void setUp() {
        // Inicializa un usuario de prueba
        usuario = new Usuarios();
        usuario.setId(1L);
        usuario.setRut("12345678-9");
        usuario.setNombres("Juan");
        usuario.setApellidos("Pérez");
        usuario.setCorreo("juan.perez@gmail.com");
        usuario.setPassword("password123");
        usuario.setTelefono("123456789");
        usuario.setDireccion("Calle Falsa 123");
        usuario.setRol("cliente");
    }

    // Test para verificar el método findAll
    @Test
    public void testFindAll() throws Exception {
        // Simula el comportamiento del servicio
        when(usuariosService.findAll()).thenReturn(List.of(usuario));

        // Realiza la petición GET y verifica la respuesta
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(usuario.getId()))
                .andExpect(jsonPath("$[0].nombres").value(usuario.getNombres()))
                .andExpect(jsonPath("$[0].apellidos").value(usuario.getApellidos()))
                .andExpect(jsonPath("$[0].correo").value(usuario.getCorreo()));
    }

    // Test para verificar el método findById
    @Test
    public void testFindById() throws Exception {
        // Simula el comportamiento del servicio
        when(usuariosService.findById(1L)).thenReturn(usuario);

        // Realiza la petición GET y verifica la respuesta
        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(usuario.getId()))
                .andExpect(jsonPath("$.nombres").value(usuario.getNombres()))
                .andExpect(jsonPath("$.apellidos").value(usuario.getApellidos()))
                .andExpect(jsonPath("$.correo").value(usuario.getCorreo()));
    }

    // Test para verificar el método save (crear usuario)
    @Test
    public void testCreateUsuario() throws Exception {
        // Simula el comportamiento del servicio
        when(usuariosService.save(any(Usuarios.class))).thenReturn(usuario);

        // Realiza la petición POST y verifica la respuesta
        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(usuario.getId()))
                .andExpect(jsonPath("$.nombres").value(usuario.getNombres()))
                .andExpect(jsonPath("$.correo").value(usuario.getCorreo()));
    }

    // Test para verificar el método update
    @Test
    public void testUpdateUsuario() throws Exception {
        // Usuario actualizado
        Usuarios usuarioActualizado = new Usuarios();
        usuarioActualizado.setId(1L);
        usuarioActualizado.setNombres("Juan Carlos");
        usuarioActualizado.setApellidos("Pérez García");
        usuarioActualizado.setCorreo("juan.carlos@gmail.com");

        // Simula el comportamiento del servicio
        when(usuariosService.save(any(Usuarios.class))).thenReturn(usuarioActualizado);

        // Realiza la petición PUT y verifica la respuesta
        mockMvc.perform(put("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombres").value("Juan Carlos"))
                .andExpect(jsonPath("$.correo").value("juan.carlos@gmail.com"));
    }


    // Test para verificar el método delete
    @Test
    public void testDeleteUsuario() throws Exception {
        // Simula el comportamiento del servicio
        doNothing().when(usuariosService).deleteById(1L);

        // Realiza la petición DELETE y verifica la respuesta
        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());

        //verifica que el servicio fue llamado
        verify(usuariosService, times(1)).deleteById(1L);
    }
}
