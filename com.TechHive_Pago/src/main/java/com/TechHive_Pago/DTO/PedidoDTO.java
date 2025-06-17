package com.TechHive_Pago.DTO;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private String rutCliente;
    private String descripcion;
    private Double total;
    private String estado;
}

