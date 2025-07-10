package com.TechHive.Inventario.Service;

import com.TechHive.Inventario.Model.Inventario;
import com.TechHive.Inventario.Repository.InventarioRepository;

import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")

public class InventarioServiceTets {

    @Autowired
    private InventarioService inventarioService;

    @MockBean
    private InventarioRepository inventarioRepository;


    @Test
    public void testFindAll() {
        Inventario inventario = createTestInventario();
        when(inventarioRepository.findAll()).thenReturn(List.of(inventario));
        List<Inventario> inventarios = inventarioService.findAll();
        assertNotNull(inventarios);
        assertEquals(1, inventarios.size());
        assertEquals(inventario.getId(), inventarios.get(0).getId());
    }

    @Test
    public void testFindById() {
        Inventario inventario = createTestInventario();
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventario));
        Inventario found = inventarioService.findById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    public void testSave() {
        Inventario inventario = createTestInventario();
        when(inventarioRepository.save(inventario)).thenReturn(inventario);
        Inventario saved = inventarioService.save(inventario);
        assertNotNull(saved);
        assertEquals("Producto Test", saved.getNombreProducto());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(inventarioRepository).deleteById(1L);
        inventarioService.deleteById(1L);
        verify(inventarioRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateStock() {
        Inventario inventario = createTestInventario();
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventario));
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventario);
        Inventario updated = inventarioService.updateStock(1L, 25);
        assertNotNull(updated);
        assertEquals(25, updated.getStock());
    }

    private Inventario createTestInventario() {
        Inventario inventario = new Inventario();
        inventario.setId(1L);
        inventario.setNombreProducto("Producto Test");
        inventario.setDescripcion("Descripción de prueba");
        inventario.setCategoria("Categoría Test");
        inventario.setPrecio(100.0);
        inventario.setStock(50);
        return inventario;
    }

}
