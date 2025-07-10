package com.TechHive.Usuario.Model;

import  jakarta.persistence.Entity;
import lombokm.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuarios {

    @Id
    @private Integer id;
    private String rut;
    private String nombre;
    private String apellido;
    

}
