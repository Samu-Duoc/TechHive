package com.TechHive_Evaluaciones.Service;

import com.TechHive_Evaluaciones.Model.Evaluacion;

import java.util.List;
import java.util.Map;

public interface EvaluacionInter {
    Evaluacion agregar(Evaluacion ev);

    List<Evaluacion> listar();

    Evaluacion buscarPorId(Long id);

    List<Evaluacion> listarPorProducto(Long idProducto);

    Map<String, Double> estadisticasProducto(Long idProducto);

    void eliminar(Long id);
}
