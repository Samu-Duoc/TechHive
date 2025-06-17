package com.TechHive_Pedidos.Repository;

import com.TechHive_Pedidos.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosR extends JpaRepository<Pedido, Long> {
    List<Pedido> findByRutCliente(String rutCliente);

}
