package com.TechHive.Inventario.Controller;

import com.TechHive.Inventario.Model.Inventario;
import com.TechHive.Inventario.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@Tag(name = "Inventario", description = "Operaciones relacionadas con inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> getAllInventarios() {
        return inventarioService.findAll();
    }

    @GetMapping("/{id}")
    public Inventario getInventarioById(@PathVariable Long id) {
        return inventarioService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Inventario> createInventario(@RequestBody Inventario inventario) {
        Inventario savedInventario = inventarioService.save(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInventario);
    }

    @DeleteMapping("/{id}")
    public void deleteInventario(@PathVariable Long id) {
        inventarioService.deleteById(id);
    }

    @PutMapping("/{id}/stock")
    public Inventario updateStock(@PathVariable Long id, @RequestBody int nuevoStock) {
        return inventarioService.updateStock(id, nuevoStock);
    }
}
