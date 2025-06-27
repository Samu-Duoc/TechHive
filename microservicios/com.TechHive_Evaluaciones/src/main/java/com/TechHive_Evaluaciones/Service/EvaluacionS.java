package com.TechHive_Evaluaciones.Service;

import com.TechHive_Evaluaciones.Model.Evaluacion;
import com.TechHive_Evaluaciones.Repository.EvaluacionR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class EvaluacionS  implements EvaluacionInter {

    @Autowired
    private EvaluacionR evaluacionR;

    @Override
    public Evaluacion agregar(Evaluacion evaluacion) {
        return evaluacionR.save(evaluacion);
    }

    @Override
    public List<Evaluacion> listar() {
        return evaluacionR.findAll();
    }

    @Override
    public Evaluacion buscarPorId(Long id) {
        return evaluacionR.findById(id).orElse(null);
    }

    @Override
    public List<Evaluacion> listarPorProducto(Long idProducto) {
        return evaluacionR.findByIdProducto(idProducto);
    }

    @Override
    public Map<String, Double> estadisticasProducto(Long productoId) {
        List<Evaluacion> evs = evaluacionR.findByIdProducto(productoId);

        Double avg = evs.stream()
                .mapToInt(Evaluacion::getCalificacion)
                .average().orElse(0);

        Double max = (double) evs.stream()
                .mapToInt(Evaluacion::getCalificacion)
                .max().orElse(0);

        Double min = (double) evs.stream()
                .mapToInt(Evaluacion::getCalificacion)
                .min().orElse(0);

        Map<String, Double> stats = new HashMap<>();
        stats.put("promedio", avg);
        stats.put("maxima", max);
        stats.put("minima", min);
        stats.put("total", (double) evs.size());
        return stats;
    }

    @Override
    public void eliminar(Long id) {
        evaluacionR.deleteById(id);
    }
}
