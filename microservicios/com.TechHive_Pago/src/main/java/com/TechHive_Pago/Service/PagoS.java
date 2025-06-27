package com.TechHive_Pago.Service;

import com.TechHive_Pago.DTO.EnvioDTO;
import com.TechHive_Pago.DTO.PedidoDTO;
import com.TechHive_Pago.Model.Pago;
import com.TechHive_Pago.Repository.PagoR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PagoS implements PagoInterface {

    @Autowired
    private PagoR pagoR;

    @Autowired
    private RestTemplate restTemplate;

    private static final String PEDIDO_URL = "http://localhost:8083/pedidos";
    private static final String ENVIO_URL = "http://localhost:8085/envios";

    @Override
    public Pago registrarPago(Pago pago) {
        try {
            ResponseEntity<PedidoDTO> response = restTemplate.getForEntity(PEDIDO_URL + "/" + pago.getIdPedido(), PedidoDTO.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                PedidoDTO pedido = response.getBody();

                if (!pago.getMonto().equals(pedido.getTotal())) {
                    throw new RuntimeException("Monto incorrecto, no coincide con el total del pedido.");
                }

                pago.setDescripcion("Pago de " + pedido.getDescripcion());
                pago.setFechaPago(LocalDateTime.now());
                pago.setEstado("Pagado");

                // REGISTRO PAGO
                Pago pagoGuardado = pagoR.save(pago);

                // NOTIFICAR A LOGÍSTICA SIN INTERRUMPIR
                try {
                    EnvioDTO envio = new EnvioDTO();
                    envio.setIdPedido(pago.getIdPedido());
                    envio.setTracking("TRACK-" + pago.getIdPedido());
                    envio.setEstado("En camino");
                    restTemplate.postForEntity(ENVIO_URL, envio, EnvioDTO.class);
                } catch (Exception e) {
                    System.out.println("⚠Fallo al notificar a logística: " + e.getMessage());
                }

                return pagoGuardado;
            } else {
                throw new RuntimeException("No se encontró el pedido.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar pago: " + e.getMessage());
        }
    }


    @Override
    public List<Pago> listarPagos() {
        return pagoR.findAll();
    }

    @Override
    public Optional<Pago> obtenerPagoPorId(Long id) {
        return pagoR.findById(id);
    }

    @Override
    public List<Pago> obtenerPorIdPedido(Long idPedido) {
        return pagoR.findByIdPedido(idPedido);
    }

    @Override
    public void eliminarPago(Long id) {
        pagoR.deleteById(id);
    }
}
