package com.TechHive.Inventario.Controller;

import com.TechHive.Inventario.Model.Inventario;
import com.TechHive.Inventario.Service.InventarioService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@WebMvcTest(InventarioController.class)
public class InventarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Inventario inventario;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        inventario.setId(1L);
        inventario.setNombreProducto("Producto Test");
        inventario.setDescripcion("Descripción del producto de prueba");
        inventario.setCategoria("Categoría Test");
        inventario.setPrecio(100.0);
        inventario.setStock(50);
    }

    @Test
    public void testGetAllInventarios() throws Exception {
        when(inventarioService.findAll()).thenReturn(List.of(inventario));
        mockMvc.perform(get("/api/inventario"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(inventario.getId()))
                .andExpect(jsonPath("$[0].nombreProducto").value(inventario.getNombreProducto()));
    }

    
    
    @Test
    public void testGetInventarioById() throws Exception {
        when(inventarioService.findById(1L)).thenReturn(inventario);
        mockMvc.perform(get("/api/inventario/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(inventario.getId()))
                .andExpect(jsonPath("$.nombreProducto").value(inventario.getNombreProducto()));
    }



    @Test
    public void testCreateInventario() throws Exception {
        when(inventarioService.save(any(Inventario.class))).thenReturn(inventario);
        mockMvc.perform(post("/api/inventario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(inventario.getId()))
                .andExpect(jsonPath("$.nombreProducto").value(inventario.getNombreProducto()));
    }

    @Test
    public void testDeleteInventario() throws Exception {
        doNothing().when(inventarioService).deleteById(1L);
        mockMvc.perform(delete("/api/inventario/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateStock() throws Exception {
        when(inventarioService.updateStock(1L, 25)).thenReturn(inventario);
        mockMvc.perform(put("/api/inventario/{id}/stock", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("25"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(inventario.getId()));
    }
}
