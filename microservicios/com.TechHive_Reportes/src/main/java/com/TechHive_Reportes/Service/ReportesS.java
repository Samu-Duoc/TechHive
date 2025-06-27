package com.TechHive_Reportes.Service;

import com.TechHive_Reportes.Model.Reportes;
import com.TechHive_Reportes.Repository.ReportesR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ReportesS implements ReportesInter {

    @Autowired
    private ReportesR reportesR;

    @Override
    public Reportes generarReporte(String nombre, String tipo, Map<String, Object> parametros) {
        // TODO: generar contenido real según parámetros
        String contenido = "{\"parametros\":" + parametros.toString() + "}";
        Reportes r = new Reportes (null, nombre, tipo, null, contenido);
        return reportesR.save(r);
    }

    @Override
    public List<Reportes> listarReportes() {
        return reportesR.findAll();
    }

    @Override
    public Map<String, Long> obtenerMetricas() {
        Map<String, Long> m = new HashMap<>();
        m.put("totalReportes", reportesR.count());
        // agrupar por tipo
        List<String> tipos = reportesR.findAll().stream()
                .map(Reportes::getTipoDeReporte).distinct().collect(Collectors.toList());
        for (String t : tipos) {
            m.put("count_" + t, reportesR.countByTipoDeReporte(t));
        }
        return m;
    }

    @Override
    public Map<String, Object> obtenerEstadisticas() {
        // Ejemplo: tamaño medio del contenido
        List<Integer> sizes = reportesR.findAll().stream()
                .map(r -> r.getContenido().length()).collect(Collectors.toList());
        double avg = sizes.stream().mapToInt(Integer::intValue).average().orElse(0);
        int max = sizes.stream().mapToInt(Integer::intValue).max().orElse(0);
        Map<String, Object> stats = new HashMap<>();
        stats.put("avgContenidoLength", avg);
        stats.put("maxContenidoLength", max);
        return stats;
    }

    @Override
    public Reportes buscarPorId(Long id) {
        return reportesR.findById(id).orElse(null);
    }


}





