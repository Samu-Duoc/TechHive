package com.TechHive_Proveedores.Service;

import com.TechHive_Proveedores.Model.SolicitudProveedor;

import java.util.List;
import java.util.Optional;

public interface SolicitudProveedorInter {
    SolicitudProveedor crear(SolicitudProveedor solicitud);

    List<SolicitudProveedor> listar();

    Optional<SolicitudProveedor> obtenerPorId(Long id);

    List<SolicitudProveedor> listarPorEstado(String estado);

    SolicitudProveedor actualizarEstado(Long id, String nuevoEstado);

    void eliminar(Long id);
}
