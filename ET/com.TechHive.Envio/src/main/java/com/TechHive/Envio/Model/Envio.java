package com.TechHive.Envio.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa un envío realizado a un destinatario.
 */
@Entity
@Table(name = "envios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {

    
    @Id
    private Long id;

    private String destinatario;

    private String direccion;

    private String ciudad;


    // Comprueba el estado de un envío -> por si esat en ruta o entregado. 
    @Size(max = 20, message = "El estado no debe superar los 20 caracteres")
    private String estado;
}
