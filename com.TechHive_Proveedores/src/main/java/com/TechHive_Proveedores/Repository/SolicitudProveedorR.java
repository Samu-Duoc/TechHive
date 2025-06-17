package com.TechHive_Proveedores.Repository;

import com.TechHive_Proveedores.Model.SolicitudProveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudProveedorR extends JpaRepository<SolicitudProveedor, Long> {
    List<SolicitudProveedor> findByEstado(String estado);
    List<SolicitudProveedor> findByProveedorCorreo(String proveedorCorreo);

}



