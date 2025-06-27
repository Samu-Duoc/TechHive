package com.TechHive.Service.ServiceClass;

import com.TechHive.Model.Inventario;
import com.TechHive.Repository.InventarioR;
import com.TechHive.Service.Interface.InventarioInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class InventarioService implements InventarioInterface {

    @Autowired
    private InventarioR inventarioR;

    @Override
    public List<Inventario> listarProductos() {
        return inventarioR.findAll();
    }

    @Override
    public Inventario buscarProductoPorId(Long id) {
        return inventarioR.findById(id).orElse(null);
    }

    @Override
    public Inventario actualizarProducto(Long id, Inventario inventario) {
        if (inventarioR.existsById(id)) {
            inventario.setId(id);
            return inventarioR.save(inventario);
        }
        return null;
    }

    @Override
    public List<Inventario> buscarProductosPorCategoria(String categoria) {
        return inventarioR.findByNombreProductoContainingIgnoreCase(categoria);
    }

    @Override
    public Inventario crearProducto(Inventario inventario) {
        return inventarioR.save(inventario);
    }

    @Override
    public void eliminarProducto(Long id) {
        inventarioR.deleteById(id);
    }

}
