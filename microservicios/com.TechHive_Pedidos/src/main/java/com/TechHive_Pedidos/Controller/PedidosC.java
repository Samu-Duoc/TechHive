package com.TechHive_Pedidos.Controller;

import com.TechHive_Pedidos.Model.Pedido;
import com.TechHive_Pedidos.Service.PedidosInterface;
import com.TechHive_Pedidos.Service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")

public class PedidosC {

    @Autowired
    private PedidosInterface pedidosInderface;
    
    @Autowired
    private PedidosService pedidosS;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido){
        Pedido nuevo = pedidosS.crearPedido(pedido);
        return ResponseEntity.status(201).body(nuevo);
    }

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidosInderface.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id){
        Pedido pedido = pedidosInderface.buscarPedidoPorId(id);
        return pedido!=null?ResponseEntity.ok(pedido):ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente/{rut}")
    public List<Pedido> buscarPedidosPorCliente(@PathVariable String rutCiente){
        return pedidosS.buscarPedidosPorRut(rutCiente);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long id){
        pedidosS.cancelarPedido(id);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidosS.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
