package com.TechHive.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCliente;
    private String direccionEntrega;
    private String telefonoContacto;

    private LocalDate fechaPedido; // Mejor usar LocalDate

    private String estadoPedido; // Ejemplo: "Pendiente", "Enviado", "Entregado"

    @Column(name = "total_pedido")
    private Double totalPedido;

    // Ejemplo de relación: un pedido puede tener varios productos
    // @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    // private List<DetallePedido> detalles;

    @ManyToMany
    @JoinTable(
        name = "pedido_inventario",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "inventario_id")
    )
    private List<Inventario> productos;

}
