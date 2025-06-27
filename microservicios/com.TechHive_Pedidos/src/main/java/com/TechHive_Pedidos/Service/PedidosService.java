package com.TechHive_Pedidos.Service;

import com.TechHive_Pedidos.DTO.ProductoDTO;
import com.TechHive_Pedidos.Model.Pedido;
import com.TechHive_Pedidos.Repository.PedidosR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class PedidosService implements PedidosInterface {

    @Autowired
    private PedidosR pedidosR;

    @Autowired
    private RestTemplate restTemplate;

    private static final String INVENTARIO_URL = "http://localhost:8082/inventario";

    @Override
    public Pedido crearPedido(Pedido pedido) {
        try {
            // Consulta el producto por su ID desde el microservicio de inventario
            ResponseEntity<ProductoDTO> response = restTemplate.getForEntity(INVENTARIO_URL + "/" + pedido.getIdProducto(), ProductoDTO.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ProductoDTO producto = response.getBody();

                //Descripción de pedido
                pedido.setDescripcion(producto.getNombreProducto());

                // Valida stock
                if (producto.getStock() < pedido.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente para el producto solicitado.");
                }

                // Calcula total
                pedido.setTotal(producto.getPrecio() * pedido.getCantidad());
                pedido.setEstado("Pendiente");
                pedido.setFechaHora(LocalDateTime.now());

                return pedidosR.save(pedido);

            } else {
                throw new RuntimeException("Producto no encontrado en inventario.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al validar producto: " + e.getMessage());
        }
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidosR.findAll();
    }

    @Override
    public Pedido buscarPedidoPorId(Long id) {
        return pedidosR.findById(id).orElse(null);
    }

    @Override
    public List<Pedido> buscarPedidosPorRut(String rutCliente) {
        return pedidosR.findByRutCliente(rutCliente);
    }

    @Override
    public void cancelarPedido(Long id) {
        Pedido pedido = buscarPedidoPorId(id);
        if (pedido != null) {
            pedido.setEstado("Cancelado");
            pedidosR.save(pedido);
        }
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidosR.deleteById(id);
    }
}