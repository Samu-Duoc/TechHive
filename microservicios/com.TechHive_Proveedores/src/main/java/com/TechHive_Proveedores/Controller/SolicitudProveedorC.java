package com.TechHive_Proveedores.Controller;

import com.TechHive_Proveedores.Model.SolicitudProveedor;
import com.TechHive_Proveedores.Service.SolicitudProveedorInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")

public class SolicitudProveedorC {

    @Autowired
    private SolicitudProveedorInter solicitudProveedorInter;

    @PostMapping
    public ResponseEntity<SolicitudProveedor> crearSolicitud(@RequestBody SolicitudProveedor solicitud) {
        SolicitudProveedor nueva = solicitudProveedorInter.crear(solicitud);
        return ResponseEntity.status(201).body(nueva);
    }

    @GetMapping
    public List<SolicitudProveedor> listarSolicitudes() {
        return solicitudProveedorInter.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudProveedor> obtenerPorId(@PathVariable Long id) {
        return solicitudProveedorInter.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-estado/{estado}")
    public List<SolicitudProveedor> listarPorEstado(@PathVariable String estado) {
        return solicitudProveedorInter.listarPorEstado(estado);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<SolicitudProveedor> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        SolicitudProveedor actualizada = solicitudProveedorInter.actualizarEstado(id, estado);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long id) {
        solicitudProveedorInter.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
