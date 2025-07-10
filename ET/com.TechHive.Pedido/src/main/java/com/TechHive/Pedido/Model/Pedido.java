package com.TechHive.Pedido.Model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {

    @Id
    private Long id;
    private String numeroPedido;
    private String rutCliente;
    private String nombreVendedor;
    private String nombreCliente;
    private String direccionEntrega;
    private String telefono;
    private LocalDate fechaPedido;
    private String estado;
    private String metodoPago;

    @Size(max = 255, message = "La descripción del pedido no debe superar los 255 caracteres")
    private String detallesPedido;

    private double totalPedido;

    // Elimina la relación con productos si solo quieres una descripción tipo boleta


}
