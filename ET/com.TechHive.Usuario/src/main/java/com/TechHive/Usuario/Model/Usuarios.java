package com.TechHive.Usuario.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuarios {

    @Id
    private Long id;
    
    private String rut;

    @Size(min = 1, max = 50)
    private String nombres;
    
    private String apellidos;

    private String correo;
    
    private String password;
    
    private String telefono;
    
    private String direccion;

    private String rol; // cliente o admin o vendedor
}
