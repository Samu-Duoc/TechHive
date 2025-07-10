package com.TechHive.Inventario.Repository;

import  com.TechHive.Inventario.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
   

}
