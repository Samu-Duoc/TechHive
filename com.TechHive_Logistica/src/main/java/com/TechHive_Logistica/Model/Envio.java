package com.TechHive_Logistica.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idPedido; //

    @Column(nullable = false)
    private String estado;

    @Column
    private String direccion;

    @Column
    private String tracking;

    @Column
    private LocalDateTime fechaEnvio;
}
