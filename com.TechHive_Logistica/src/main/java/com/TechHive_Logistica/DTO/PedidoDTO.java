package com.TechHive_Logistica.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data

public class PedidoDTO {

    private Long id;
    private String rutCliente;
    private String descripcion;

}

