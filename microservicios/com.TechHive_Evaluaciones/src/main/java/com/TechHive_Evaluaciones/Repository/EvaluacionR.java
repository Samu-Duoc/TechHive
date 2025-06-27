package com.TechHive_Evaluaciones.Repository;

import com.TechHive_Evaluaciones.Model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EvaluacionR extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByIdProducto(Long idProducto);
    List<Evaluacion> findByUsuarioId(Long UsuarioId);

}
