package com.TechHive.Envio.Controller;

import com.TechHive.Envio.Model.Envio;
import com.TechHive.Envio.Service.EnvioService;

import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@Tag(name = "Envio", description = "Operaciones relacionadas con envíos")

public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> getAllEnvios() {
        return envioService.findAll();
    }

    @GetMapping("/{id}")
    public Envio getEnvioById(@PathVariable Long id) {
        return envioService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Envio> createEnvio(@RequestBody Envio envio) {
        Envio savedEnvio = envioService.save(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEnvio);
    }

    @PutMapping("/{id}")
    public Envio updateEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        envio.setId(id);
        return envioService.save(envio);
    }

    @DeleteMapping("/{id}")
    public void deleteEnvio(@PathVariable Long id) {
        envioService.deleteById(id);
    }

}
