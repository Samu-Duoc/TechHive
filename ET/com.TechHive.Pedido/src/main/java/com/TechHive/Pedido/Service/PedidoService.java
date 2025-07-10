package com.TechHive.Pedido.Service;

import org.springframework.stereotype.Service;
import com.TechHive.Pedido.Repository.PedidoRepository;
import com.TechHive.Pedido.Model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


    // Aqui se actualiza un pedido existente.
    // El objeto pedidoDetails debe contener el ID del pedido a actualizar.
    // Si el ID no existe, se retornará null.
    // Si el ID existe, se actualizarán los campos del pedido con los valores de pedido
    public Pedido updatePedido(Long id, Pedido pedidoDetails) {
        Pedido existingPedido = getPedidoById(id);
        if (existingPedido != null) {
            existingPedido.setNumeroPedido(pedidoDetails.getNumeroPedido());
            existingPedido.setRutCliente(pedidoDetails.getRutCliente());
            existingPedido.setNombreVendedor(pedidoDetails.getNombreVendedor());
            existingPedido.setNombreCliente(pedidoDetails.getNombreCliente());
            existingPedido.setDireccionEntrega(pedidoDetails.getDireccionEntrega());
            existingPedido.setTelefono(pedidoDetails.getTelefono());
            existingPedido.setFechaPedido(pedidoDetails.getFechaPedido());
            existingPedido.setEstado(pedidoDetails.getEstado());
            existingPedido.setMetodoPago(pedidoDetails.getMetodoPago());
            existingPedido.setDetallesPedido(pedidoDetails.getDetallesPedido());
            existingPedido.setTotalPedido(pedidoDetails.getTotalPedido());
            return pedidoRepository.save(existingPedido);
        }
        return null;
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

}
