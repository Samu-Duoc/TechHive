package com.TechHive_Productos.Service;

import com.TechHive_Productos.Model.Inventario;

import java.util.List;

public interface Inventario_Inter {


    List<Inventario> listarProductos();

    Inventario buscarProductoPorId(Long id);  // ← corregido

    Inventario actualizarProducto(Inventario inventario);  // ← corregido

    List<Inventario> buscarProductoPorNombre(String nombreProducto);

    void eliminarProducto(Long id);
}

