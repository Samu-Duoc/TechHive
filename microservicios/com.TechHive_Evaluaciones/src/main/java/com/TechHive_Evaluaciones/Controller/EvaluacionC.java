package com.TechHive_Evaluaciones.Controller;

import com.TechHive_Evaluaciones.Model.Evaluacion;
import com.TechHive_Evaluaciones.Service.EvaluacionInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/evaluaciones")

public class EvaluacionC {

    @Autowired
    private EvaluacionInter evaluacionInter;

    @PostMapping
    public ResponseEntity<Evaluacion> agregar(@RequestBody Evaluacion evaluacion) {
        Evaluacion nueva = evaluacionInter.agregar(evaluacion);
        return ResponseEntity.status(201).body(nueva);
    }

    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionInter.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> buscar(@PathVariable Long id) {
        Evaluacion ev = evaluacionInter.buscarPorId(id);
        return ev != null ? ResponseEntity.ok(ev) : ResponseEntity.notFound().build();
    }

    @GetMapping("/producto/{pid}")
    public List<Evaluacion> listarPorProducto(@PathVariable("pid") Long pid) {
        return evaluacionInter.listarPorProducto(pid);
    }

    @GetMapping("/stat/producto/{pid}")
    public ResponseEntity<Map<String,Double>> estadisticas(@PathVariable("pid") Long pid) {
        return ResponseEntity.ok(evaluacionInter.estadisticasProducto(pid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        evaluacionInter.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}