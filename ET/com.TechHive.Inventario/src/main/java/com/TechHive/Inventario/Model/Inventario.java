package com.TechHive.Inventario.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Inventario {
    @Id
    private Long id;
    private String nombreProducto;
    private String descripcion;
    @Size(max = 5, message = "El stock no debe tener más de 5 dígitos")
    private int stock;
    private String categoria;
    private double precio;




}
