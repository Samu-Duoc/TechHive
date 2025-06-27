package com.TechHive.Controller;

import com.TechHive.Model.Inventario;
import com.TechHive.Service.Interface.InventarioInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")

public class InventarioC {

    @Autowired
    private InventarioInterface inventarioService;

    @GetMapping("/productos")
    public List<Inventario> listarProductos() {
        return inventarioService.listarProductos();
    }

    @GetMapping("/producto/{id}")
    public Inventario buscarProductoPorId(@PathVariable Long id) {
        return inventarioService.buscarProductoPorId(id);
    }

    @PutMapping("/producto/{id}")
    public Inventario actualizarProducto(@PathVariable Long id, @RequestBody Inventario inventario) {
        return inventarioService.actualizarProducto(id, inventario);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Inventario> buscarProductosPorCategoria(@PathVariable String categoria) {
        return inventarioService.buscarProductosPorCategoria(categoria);
    }

    @DeleteMapping("/producto/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        inventarioService.eliminarProducto(id);
    }

    @PostMapping("/producto")
    public Inventario crearProducto(@RequestBody Inventario inventario) {
        return inventarioService.crearProducto(inventario);
    }
}
