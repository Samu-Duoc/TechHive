package com.TechHive.Pedido.Service;

import com.TechHive.Pedido.Model.Pedido;
import com.TechHive.Pedido.Repository.PedidoRepository;

import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")

public class PedidoServiceTests {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    private Pedido createTestPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setNumeroPedido("12345678");
        pedido.setRutCliente("12345678-9");
        pedido.setNombreVendedor("Vendedor Test");
        pedido.setNombreCliente("Cliente Test");
        pedido.setDireccionEntrega("Calle Falsa 123");
        pedido.setTelefono("987654321");
        pedido.setFechaPedido(java.time.LocalDate.now());
        pedido.setEstado("Pendiente");
        pedido.setMetodoPago("Efectivo");
        pedido.setDetallesPedido("Producto Test x2");
        pedido.setTotalPedido(100.0);
        return pedido;
    }

    @Test
    public void testCreatePedido() {
        Pedido pedido = createTestPedido();
        when(pedidoRepository.save(pedido)).thenReturn(pedido);
        Pedido saved = pedidoService.createPedido(pedido);
        assertNotNull(saved);
        assertEquals("12345678", saved.getNumeroPedido());
    }

    @Test
    public void testGetAllPedidos() {
        Pedido pedido = createTestPedido();
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        assertEquals(pedido.getId(), pedidos.get(0).getId());
    }

    @Test
    public void testGetPedidoById() {
        Pedido pedido = createTestPedido();
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        Pedido found = pedidoService.getPedidoById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    public void testUpdatePedido() {
        Pedido pedido = createTestPedido();
        Pedido updatedDetails = createTestPedido();
        updatedDetails.setEstado("Entregado");
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(updatedDetails);
        Pedido updated = pedidoService.updatePedido(1L, updatedDetails);
        assertNotNull(updated);
        assertEquals("Entregado", updated.getEstado());
    }

    @Test
    public void testDeletePedido() {
        doNothing().when(pedidoRepository).deleteById(1L);
        pedidoService.deletePedido(1L);
        verify(pedidoRepository, times(1)).deleteById(1L);
    }
}
