package com.TechHive;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.TechHive.Model.Inventario;
import com.TechHive.Repository.InventarioR;
import com.TechHive.Service.ServiceClass.InventarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class InventarioServiceTest {

    //Clase de servicio que se va a probar
    @Autowired
    private InventarioService inventarioService;

    //Crea un mock del repositorio de Inventario para simular su comportamiento.
    @MockBean
    private InventarioR inventarioR;

    //Métodos de prueba para el servicio de inventario
    @Test
    public void testListarProductos() {
        when(inventarioR.findAll()).thenReturn(List.of(new Inventario()));
        List<Inventario> productos = inventarioService.listarProductos();
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    //Hace una prueba para buscar productos por categoría
    @Test
    public void testBuscarProductoPorId() {
        Long id = 1L;
        Inventario inventario = new Inventario();
        inventario.setId(id);
        when(inventarioR.findById(id)).thenReturn(Optional.of(inventario));
        Inventario found = inventarioService.buscarProductoPorId(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    //Hace una prueba para guardar productos
    @Test
    public void testGuardarProducto() {
        Inventario inventario = new Inventario();
        inventario.setNombreProducto("Laptop");
        when(inventarioR.save(inventario)).thenReturn(inventario);
        Inventario saved = inventarioService.crearProducto(inventario);
        assertNotNull(saved);
        assertEquals("Laptop", saved.getNombreProducto());
    }

    //Hace una prueba para eliminar productos
    @Test
    public void testEliminarProducto() {
        Long id = 1L;
        doNothing().when(inventarioR).deleteById(id);
        inventarioService.eliminarProducto(id);
        verify(inventarioR, times(1)).deleteById(id);
    }
}
