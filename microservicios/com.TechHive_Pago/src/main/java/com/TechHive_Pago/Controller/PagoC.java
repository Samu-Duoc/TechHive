package com.TechHive_Pago.Controller;

import com.TechHive_Pago.Model.Pago;
import com.TechHive_Pago.Service.PagoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoC {

    @Autowired
    private PagoInterface pagoS;

    // Crear un nuevo pago
    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoS.registrarPago(pago);
        return ResponseEntity.status(201).body(nuevoPago);
    }

    // Listar todos los pagos
    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos() {
        return ResponseEntity.ok(pagoS.listarPagos());
    }

    // Obtener un pago por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPagoPorId(@PathVariable Long id) {
        return pagoS.obtenerPagoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener pagos por ID del pedido
    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<Pago>> obtenerPagosPorIdPedido(@PathVariable Long idPedido) {
        List<Pago> pagos = pagoS.obtenerPorIdPedido(idPedido);
        return ResponseEntity.ok(pagos);
    }

    // Eliminar un pago por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagoS.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
