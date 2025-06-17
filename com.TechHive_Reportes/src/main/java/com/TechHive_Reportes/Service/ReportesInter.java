package com.TechHive_Reportes.Service;

import com.TechHive_Reportes.Model.Reportes;

import java.util.List;
import java.util.Map;

public interface ReportesInter {

    Reportes generarReporte(String nombre, String tipo, Map<String, Object> parametros);

    List<Reportes> listarReportes();

    Map<String, Long> obtenerMetricas();// total, por tipo

    Map<String, Object> obtenerEstadisticas();  //avgSize, maxSize, etc.

    Reportes buscarPorId(Long id);

}

