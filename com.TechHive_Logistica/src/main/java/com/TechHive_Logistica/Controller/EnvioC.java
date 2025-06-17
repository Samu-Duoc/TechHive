package com.TechHive_Logistica.Controller;

import com.TechHive_Logistica.Model.Envio;
import com.TechHive_Logistica.Service.EnvioInterface;
import com.TechHive_Logistica.DTO.EnvioDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioC {

    @Autowired
    private EnvioInterface envioS;

    @PostMapping
    public ResponseEntity<Envio> registrar(@RequestBody EnvioDTO envioDTO) {
        Envio envio = new Envio();
        envio.setIdPedido(envioDTO.getIdPedido());
        return ResponseEntity.status(201).body(envioS.registrarEnvio(envio));
    }


    @PutMapping("/{id}/estado")
    public ResponseEntity<Envio> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(envioS.actualizarEstado(id, estado));
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<Envio>> buscarPorPedido(@PathVariable Long idPedido) {
        return ResponseEntity.ok(envioS. buscarPorPedidoId(idPedido));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        envioS.eliminarEnvio(id);
        return ResponseEntity.noContent().build();
    }
}

