package com.TechHive_Proveedores.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Datos del proveedor (almacenados junto a la solicitud) */
    private String proveedorNombre;
    private String proveedorCorreo;
    private String proveedorTelefono;

    /* Detalles de la solicitud */
    private String producto;
    private int cantidad;
    private String estado;
    private LocalDateTime fechaSolicitud;

    @PrePersist
    public void prePersist() {
        this.estado = this.estado == null ? "Pendiente" : this.estado;
        this.fechaSolicitud = LocalDateTime.now();
    }
}