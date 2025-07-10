package com.TechHive.Pedido.Controller;

import com.TechHive.Pedido.Model.Pedido;
import com.TechHive.Pedido.Service.PedidoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import  io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedido", description = "Api de pedidos")

public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.createPedido(pedido);
    }

    @PutMapping("/{id}")
    public Pedido updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.updatePedido(id, pedido);
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
    }
}
