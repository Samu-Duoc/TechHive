package com.TechHive.Pedido.Repository;

import com.TechHive.Pedido.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
