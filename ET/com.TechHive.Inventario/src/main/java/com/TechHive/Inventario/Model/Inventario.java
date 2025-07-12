package com.TechHive.Inventario.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreProducto;
    
    private String descripcion;
    
    @Size(min = 1, max = 100)
    private int stock;
    
    private String categoria;
    
    private double precio;
}
