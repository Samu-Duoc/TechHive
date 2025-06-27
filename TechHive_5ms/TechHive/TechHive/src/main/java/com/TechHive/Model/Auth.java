package com.TechHive.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Auth{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String rut;
    private String correo;
    private String contrasenia;
    private String rol;
    private String comuna;
    private String direccion;
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
}