package com.TechHive_Proveedores.Service;

import com.TechHive_Proveedores.Model.SolicitudProveedor;
import com.TechHive_Proveedores.Repository.SolicitudProveedorR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudProveedorS implements SolicitudProveedorInter {

  @Autowired
  public SolicitudProveedorR solicitudProveedorR;

    @Override
    public SolicitudProveedor crear(SolicitudProveedor solicitud) {
        return solicitudProveedorR.save(solicitud);
    }

    @Override
    public List<SolicitudProveedor> listar() {
        return solicitudProveedorR.findAll();
    }

    @Override
    public Optional<SolicitudProveedor> obtenerPorId(Long id) {
        return solicitudProveedorR.findById(id);
    }

    @Override
    public List<SolicitudProveedor> listarPorEstado(String estado) {
        return solicitudProveedorR.findByEstado(estado);
    }

    @Override
    public SolicitudProveedor actualizarEstado(Long id, String nuevoEstado) {
        return solicitudProveedorR.findById(id).map(s -> {
            s.setEstado(nuevoEstado);
            return solicitudProveedorR.save(s);
        }).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        solicitudProveedorR.deleteById(id);
    }
}
