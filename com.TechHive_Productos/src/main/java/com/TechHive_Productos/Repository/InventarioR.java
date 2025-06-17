package com.TechHive_Productos.Repository;

import com.TechHive_Productos.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface InventarioR extends JpaRepository<Inventario, Long> {
    List<Inventario> findByNombreProductoContainingIgnoreCase(String nombreProducto);
}
