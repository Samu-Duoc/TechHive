package com.TechHive.Auth.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.TechHive.Auth.Model.Auth;
import com.TechHive.Auth.Service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    private Auth auth;

    @BeforeEach
    void setUp() {
        // Crea el objeto auth con datos fijos
        auth = new Auth();
        auth.setId(1L); // Asigna un ID fijo para pruebas
        auth.setCorreo("john.doe@mail.com");
        auth.setPassword("password123");
    }

    @Test
    public void testGetAuth() throws Exception {
        // Simula la respuesta del servicio
        when(authService.findAll()).thenReturn(List.of(auth));

        mockMvc.perform(get("/api/auth")) // Asegúrate de que la ruta sea la correcta según el controlador
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(auth.getId()))
                .andExpect(jsonPath("$[0].correo").value(auth.getCorreo()))
                .andExpect(jsonPath("$[0].password").value(auth.getPassword()));
    }

    @Test
    public void testCreateAuth() throws Exception {
        // Simula que el servicio guarda un nuevo Auth
        when(authService.save(auth)).thenReturn(auth);

        mockMvc.perform(post("/api/auth") // Asegúrate de que la ruta sea la correcta según el controlador
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(auth)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(auth.getId()))
                .andExpect(jsonPath("$.correo").value(auth.getCorreo()))
                .andExpect(jsonPath("$.password").value(auth.getPassword()));
    }

    @Test
    public void testDeleteAuth() throws Exception {
        // Simula la eliminación del usuario por id
        doNothing().when(authService).deleteById(auth.getId()); // Cambiado para usar deleteById

        mockMvc.perform(delete("/api/auth/{id}", auth.getId())) // Ruta ajustada según el controlador para eliminar por ID
                .andExpect(status().isNoContent());
    }
}
