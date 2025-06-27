package com.TechHive_Pago.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioDTO {

    private Long idPedido;
    private String direccion;
    private String estado;
    private String tracking;
}
