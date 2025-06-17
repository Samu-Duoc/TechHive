package com.TechHive_Pedidos.Service;

import com.TechHive_Pedidos.Model.Pedido;

import java.util.List;

public interface PedidosInterface {

    Pedido crearPedido(Pedido pedido);

    List<Pedido> listarPedidos();

    Pedido buscarPedidoPorId(Long id);

    List<Pedido> buscarPedidosPorRut(String rutCliente);

    void cancelarPedido(Long id);

    void eliminarPedido(Long id);
}
