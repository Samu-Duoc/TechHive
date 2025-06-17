package com.TechHive_Reportes.Controller;

import com.TechHive_Reportes.Model.Reportes;
import com.TechHive_Reportes.Service.ReportesInter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")

public class ReportesC {

    @Autowired
    private ReportesInter reportesInter;


    @PostMapping("/generar")
    public ResponseEntity<Reportes> generar(
            @RequestParam String nombre,
            @RequestParam String tipoDeReporte,
            @RequestBody Map<String,Object> parametros) {

        Reportes rep = reportesInter.generarReporte(nombre, tipoDeReporte, parametros);
        return ResponseEntity.status(201).body(rep);
    }

    @GetMapping
    public List<Reportes> listar() {
        return reportesInter.listarReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reportes> buscar(@PathVariable Long id) {
        Reportes rep = reportesInter.buscarPorId(id);
        return rep != null
                ? ResponseEntity.ok(rep)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/metricas")
    public Map<String,Long> metricas() {
        return reportesInter.obtenerMetricas();
    }

    @GetMapping("/estadisticas")
    public Map<String,Object> estadisticas() {
        return reportesInter.obtenerEstadisticas();
    }
}