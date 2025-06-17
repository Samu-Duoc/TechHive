package com.TechHive_Pedidos.DTO;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String nombreProducto;
    private Integer stock;
    private Double precio;

}
