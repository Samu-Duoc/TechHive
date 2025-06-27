package com.TechHive.Service.Interface;

import com.TechHive.Model.Inventario;

import java.util.List;

public interface InventarioInterface {

    List<Inventario> listarProductos();

    Inventario buscarProductoPorId(Long id);

    Inventario actualizarProducto(Long id, Inventario inventario);

    List<Inventario> buscarProductosPorCategoria(String categoria);

    Inventario crearProducto(Inventario inventario);

    void eliminarProducto(Long id);

}
