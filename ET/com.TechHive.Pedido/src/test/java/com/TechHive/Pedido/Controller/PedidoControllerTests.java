package com.TechHive.Pedido.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.TechHive.Pedido.Model.Pedido;
import com.TechHive.Pedido.Service.PedidoService;

import java.util.List;
@WebMvcTest(PedidoController.class) // Asegúrate de que el controlador esté correctamente importado
public class PedidoControllerTests {

    @Autowired
    private MockMvc mockMvc; // MockMvc para simular peticiones HTTP

    @MockBean
    private PedidoService pedidoService; // Servicio a probar, simulado con Mockito

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos a JSON

    private Pedido pedido; // Objeto Pedido para las pruebas

    @BeforeEach
    void setUp() {
        // Inicializa un pedido de prueba
        pedido = new Pedido();
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
    }

    @Test
    public void testGetAllPedidos() throws Exception {
        when(pedidoService.getAllPedidos()).thenReturn(List.of(pedido));
        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(pedido.getId()))
                .andExpect(jsonPath("$[0].numeroPedido").value(pedido.getNumeroPedido()));
    }

    @Test
    public void testGetPedidoById() throws Exception {
        when(pedidoService.getPedidoById(1L)).thenReturn(pedido);
        mockMvc.perform(get("/api/pedidos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(pedido.getId()))
                .andExpect(jsonPath("$.numeroPedido").value(pedido.getNumeroPedido()));
    }

    @Test
    public void testCreatePedido() throws Exception {
        when(pedidoService.createPedido(any(Pedido.class))).thenReturn(pedido);
        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(pedido.getId()))
                .andExpect(jsonPath("$.numeroPedido").value(pedido.getNumeroPedido()));
    }

    @Test
    public void testUpdatePedido() throws Exception {
        when(pedidoService.updatePedido(eq(1L), any(Pedido.class))).thenReturn(pedido);
        mockMvc.perform(put("/api/pedidos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(pedido.getId()))
                .andExpect(jsonPath("$.numeroPedido").value(pedido.getNumeroPedido()));
    }

    @Test
    public void testDeletePedido() throws Exception {
        doNothing().when(pedidoService).deletePedido(1L);
        mockMvc.perform(delete("/api/pedidos/{id}", 1L))
                .andExpect(status().isOk());
    }
}
