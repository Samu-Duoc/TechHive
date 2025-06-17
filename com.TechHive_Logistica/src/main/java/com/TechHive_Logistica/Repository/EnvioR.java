package com.TechHive_Logistica.Repository;

import com.TechHive_Logistica.Model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvioR extends JpaRepository<Envio, Long> {
    List<Envio> findByIdPedido(Long idPedido);
}
