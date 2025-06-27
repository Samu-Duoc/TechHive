package com.TechHive_Productos.Controller;

import com.TechHive_Productos.Model.Inventario;
import com.TechHive_Productos.Service.Inventario_Inter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")

public class InventarioC {

    @Autowired
    private Inventario_Inter inventarioS;

    // Crear producto
    @PostMapping
    public ResponseEntity<Inventario> crearProducto(@RequestBody Inventario inventario) {
        Inventario creado = inventarioS.actualizarProducto(inventario);
        return ResponseEntity.status(201).body(creado);
    }

    // Listar todos los productos
    @GetMapping
    public List<Inventario> listarProducto() {
        return inventarioS.listarProductos();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerProducto(@PathVariable Long id) {
        Inventario producto = inventarioS.buscarProductoPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    // Buscar producto por nombres
    @GetMapping("/buscar")
    public ResponseEntity<List<Inventario>> buscarProductoPorNombre(@RequestParam String nombreProducto) {
        List<Inventario> resultado = inventarioS.buscarProductoPorNombre(nombreProducto);
        return ResponseEntity.ok(resultado);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarProducto(@PathVariable Long id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        Inventario actualizado = inventarioS.actualizarProducto(inventario);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar producto por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        inventarioS.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
