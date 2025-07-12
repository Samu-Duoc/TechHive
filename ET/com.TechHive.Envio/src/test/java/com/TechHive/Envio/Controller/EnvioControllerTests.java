package com.TechHive.Envio.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.TechHive.Envio.Model.Envio;
import com.TechHive.Envio.Service.EnvioService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(EnvioController.class)

public class EnvioControllerTests {

    @Autowired
    private MockMvc mockMvc; // MockMvc para simular peticiones HTTP

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos a JSON

    @MockBean
    private EnvioService envioService; // Servicio a probar, simulado con Mockito

    private Envio envio; // Objeto Envio para las pruebas

    @BeforeEach
    void setUp() {
        // Inicializa un envío de prueba
        envio = new Envio();
        envio.setId(1L);
        envio.setEstado("En ruta");
        envio.setDireccion("Calle Falsa 123");
        envio.setCiudad("Ciudad Ficticia");
        envio.setDestinatario("Juan Perez");
    }

    // Test para verificar el método findAll
    @Test
    public void testFindAll() throws Exception {
        when(envioService.findAll()).thenReturn(List.of(envio));

        mockMvc.perform(get("/api/envios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(envio.getId()))
                .andExpect(jsonPath("$[0].estado").value(envio.getEstado()));
    }

    // Test para verificar el método findById
    @Test
    public void testFindById() throws Exception {
        Long id = 1L;
        when(envioService.findById(id)).thenReturn(envio);

        mockMvc.perform(get("/api/envios/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(envio.getId()))
                .andExpect(jsonPath("$.estado").value(envio.getEstado()));
    }
    // Test para verificar el método save
    @Test
    public void testSave() throws Exception {
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        mockMvc.perform(post("/api/envios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(envio.getId()))
                .andExpect(jsonPath("$.estado").value(envio.getEstado()));
    }
}