package com.TechHive_Pago.Repository;

import com.TechHive_Pago.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoR extends JpaRepository<Pago, Long> {
    List<Pago> findByIdPedido(Long idPedido);
}
