package com.TechHive_Productos.Service;

import com.TechHive_Productos.Model.Inventario;
import com.TechHive_Productos.Repository.InventarioR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioS implements Inventario_Inter{

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
    public Inventario actualizarProducto(Inventario inventario) {
        return inventarioR.save(inventario);
    }

    @Override
    public void eliminarProducto(Long id) {
        inventarioR.deleteById(id);
    }

    @Override
    public List<Inventario> buscarProductoPorNombre(String nombreProducto) {
        return inventarioR.findByNombreProductoContainingIgnoreCase(nombreProducto);
    }
}
