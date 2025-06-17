package com.TechHive_Reportes.Repository;

import com.TechHive_Reportes.Model.Reportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReportesR extends JpaRepository<Reportes, Long> {
    Long countByTipoDeReporte(String tipoDeReporte);
}
