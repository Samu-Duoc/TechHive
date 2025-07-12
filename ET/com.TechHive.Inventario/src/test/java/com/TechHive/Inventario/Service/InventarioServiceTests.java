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
public class InventarioServiceTests {

    @Autowired
    private InventarioService inventarioService;

    @MockBean
    private InventarioRepository inventarioRepository;

    /**
     * Prueba que el método findAll retorne todos los inventarios.
     */
    @Test
    public void testFindAll() {
        Inventario inventario = createInventario();
        when(inventarioRepository.findAll()).thenReturn(List.of(inventario));
        List<Inventario> inventarios = inventarioService.findAll();
        assertNotNull(inventarios);
        assertEquals(1, inventarios.size());
        assertEquals(inventario.getId(), inventarios.get(0).getId());
    }

    /**
     * Prueba que el método findById retorne el inventario correcto por ID.
     */
    @Test
    public void testFindById() {
        Long id = 1L;
        Inventario inventario = createInventario();
        when(inventarioRepository.findById(id)).thenReturn(Optional.of(inventario));
        Inventario foundInventario = inventarioService.findById(id);
        assertNotNull(foundInventario);
        assertEquals(inventario.getId(), foundInventario.getId());
    }

    /**
     * Prueba que el método findById retorne null cuando no encuentra el inventario.
     */
    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;
        when(inventarioRepository.findById(id)).thenReturn(Optional.empty());
        Inventario foundInventario = inventarioService.findById(id);
        assertNull(foundInventario);
    }

    /**
     * Prueba que el método save guarde correctamente un inventario.
     */
    @Test
    public void testSave() {
        Inventario inventario = createInventario();
        when(inventarioRepository.save(inventario)).thenReturn(inventario);
        Inventario savedInventario = inventarioService.save(inventario);
        assertNotNull(savedInventario);
        assertEquals("Producto Test", savedInventario.getNombreProducto());
    }

    /**
     * Prueba que el método save permita actualizar un producto (forma tradicional).
     */
    @Test
    public void testUpdate() {
        Inventario inventario = createInventario();
        inventario.setNombreProducto("Producto Actualizado");
        when(inventarioRepository.save(inventario)).thenReturn(inventario);
        Inventario updatedInventario = inventarioService.save(inventario);
        assertNotNull(updatedInventario);
        assertEquals("Producto Actualizado", updatedInventario.getNombreProducto());
    }

    /**
     * Prueba que el método deleteById elimine el inventario por ID.
     */
    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(inventarioRepository).deleteById(id);
        inventarioService.deleteById(id);
        verify(inventarioRepository, times(1)).deleteById(id);
    }

    /**
     * Prueba que el método updateStock actualice correctamente el stock de un producto.
     */
    @Test
    public void testUpdateStock() {
        Long id = 1L;
        Inventario inventario = createInventario();
        Inventario inventarioActualizado = createInventario();
        inventarioActualizado.setStock(25);
        
        // Mock para findById
        when(inventarioRepository.findById(id)).thenReturn(Optional.of(inventario));
        // Mock para save
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioActualizado);
        
        Inventario updatedInventario = inventarioService.updateStock(id, 25);
        assertNotNull(updatedInventario);
        assertEquals(25, updatedInventario.getStock());
        assertEquals(id, updatedInventario.getId());
    }

    /**
     * Prueba que el método updateStock retorne null cuando no encuentra el producto.
     */
    @Test
    public void testUpdateStockNotFound() {
        Long id = 1L;
        when(inventarioRepository.findById(id)).thenReturn(Optional.empty());
        
        Inventario updatedInventario = inventarioService.updateStock(id, 25);
        assertNull(updatedInventario);
        verify(inventarioRepository).findById(id);
        verify(inventarioRepository, never()).save(any());
    }

    private Inventario createInventario() {
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
