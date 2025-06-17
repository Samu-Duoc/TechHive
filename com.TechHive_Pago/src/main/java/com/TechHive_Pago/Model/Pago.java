package com.TechHive_Pago.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private Long idPedido;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private String numeroDePago;

    @Column(nullable = false)
    private String metodoDePago;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime fechaPago;

}
