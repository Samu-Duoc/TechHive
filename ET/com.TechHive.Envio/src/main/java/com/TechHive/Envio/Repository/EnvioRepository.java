package com.TechHive.Envio.Repository;

import com.TechHive.Envio.Model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    // Buscar envíos por estado
    java.util.List<Envio> findByEstado(String estado);
}
