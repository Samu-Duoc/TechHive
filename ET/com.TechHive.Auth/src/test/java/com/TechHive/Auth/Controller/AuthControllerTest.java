package com.TechHive.Auth.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.TechHive.Auth.Model.Auth;
import com.TechHive.Auth.Service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(AuthController.class) // Asegúrate de que el controlador esté correctamente importado
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc para simular peticiones HTTP

    @MockBean
    private AuthService authService; // Servicio a probar, simulado con Mockito

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos a JSON

    private Auth auth; // Objeto Auth para las pruebas

    @BeforeEach
    void setUp() {
        // Inicializa un objeto auth de prueba
        auth = new Auth();
        auth.setId(1L); // Asigna un ID fijo para pruebas
        auth.setCorreo("john.doe@mail.com");
        auth.setPassword("password123");
    }

    // Test para verificar el método findAll
    @Test
    public void testFindAll() throws Exception {
        // Simula el comportamiento del servicio
        when(authService.findAll()).thenReturn(List.of(auth));

        // Realiza la petición GET y verifica la respuesta
        mockMvc.perform(get("/api/auth"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(auth.getId()))
                .andExpect(jsonPath("$[0].correo").value(auth.getCorreo()))
                .andExpect(jsonPath("$[0].password").value(auth.getPassword()));
    }

    // Test para verificar el método findById
    @Test
    public void testFindById() throws Exception {
        // Simula el comportamiento del servicio
        when(authService.findById(1L)).thenReturn(auth);

        // Realiza la petición GET y verifica la respuesta
        mockMvc.perform(get("/api/auth/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(auth.getId()))
                .andExpect(jsonPath("$.correo").value(auth.getCorreo()))
                .andExpect(jsonPath("$.password").value(auth.getPassword()));
    }


    // Test para verificar el método update
    @Test
    public void testUpdateAuth() throws Exception {
        // Auth actualizado
        Auth authActualizado = new Auth();
        authActualizado.setId(1L);
        authActualizado.setCorreo("john.updated@mail.com");
        authActualizado.setPassword("newpassword123");

        // Simula el comportamiento del servicio
        when(authService.save(any(Auth.class))).thenReturn(authActualizado);

        // Realiza la petición PUT y verifica la respuesta
        mockMvc.perform(put("/api/auth/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.correo").value("john.updated@mail.com"))
                .andExpect(jsonPath("$.password").value("newpassword123"));
    }

    // Test para verificar el método delete
    @Test
    public void testDeleteAuth() throws Exception {
        // Simula el comportamiento del servicio
        doNothing().when(authService).deleteById(1L);

        // Realiza la petición DELETE y verifica la respuesta
        mockMvc.perform(delete("/api/auth/1"))
                .andExpect(status().isOk());

        // Verifica que el servicio fue llamado
        verify(authService, times(1)).deleteById(1L);
    }
}
