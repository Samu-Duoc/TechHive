package com.TechHive_Logistica.Service;

import com.TechHive_Logistica.DTO.PedidoDTO;
import com.TechHive_Logistica.DTO.PagoDTO;
import com.TechHive_Logistica.DTO.UsuarioDTO;
import com.TechHive_Logistica.Model.Envio;
import com.TechHive_Logistica.Repository.EnvioR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EnvioS implements EnvioInterface {

    @Autowired
    private EnvioR envioR;

    @Autowired
    private RestTemplate restTemplate;

    private static final String PEDIDOS_URL = "http://localhost:8083/pedidos";
    private static final String PAGOS_URL = "http://localhost:8084/pagos";
    private static final String USUARIOS_URL = "http://localhost:8081/usuarios";

    @Override
    public Envio registrarEnvio(Envio envio) {
        // Obtener el pedido
        ResponseEntity<PedidoDTO> pedidoResp = restTemplate.getForEntity(
                PEDIDOS_URL + "/" + envio.getIdPedido(), PedidoDTO.class);

        PedidoDTO pedido = pedidoResp.getBody();

        // Verificar si el pedido fue pagado
        ResponseEntity<List<PagoDTO>> pagosResp = restTemplate.exchange(
                PAGOS_URL + "/pedido/" + pedido.getId(),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PagoDTO>>() {}
        );

        List<PagoDTO> pagos = pagosResp.getBody();
        boolean pagado = pagos.stream().anyMatch(p -> "Pagado".equalsIgnoreCase(p.getEstado()));

        if (!pagado) {
            throw new RuntimeException("El pedido aún no ha sido pagado.");
        }

        // Obtener datos del usuario para extraer dirección
        ResponseEntity<UsuarioDTO> usuarioResp = restTemplate.getForEntity(
                USUARIOS_URL + "/rut/" + pedido.getRutCliente(),
                UsuarioDTO.class
        );
        UsuarioDTO usuario = usuarioResp.getBody();

        envio.setDireccion(usuario.getDireccion());
        envio.setEstado("En preparación");
        envio.setFechaEnvio(LocalDateTime.now());
        envio.setTracking("TH-" + UUID.randomUUID());

        return envioR.save(envio);
    }

    @Override
    public Envio actualizarEstado(Long id, String nuevoEstado) {
        Envio envio = envioR.findById(id).orElseThrow(() -> new RuntimeException("Envío no encontrado"));
        envio.setEstado(nuevoEstado);
        envio.setFechaEnvio(LocalDateTime.now());
        return envioR.save(envio);
    }

    @Override
    public List<Envio>  buscarPorPedidoId(Long idPedido) {
        return envioR.findByIdPedido(idPedido);
    }

    @Override
    public void eliminarEnvio(Long id) {
        envioR.deleteById(id);
    }
}