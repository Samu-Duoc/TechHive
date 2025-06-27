package com.TechHive_Logistica.Service;

import com.TechHive_Logistica.Model.Envio;
import java.util.List;

public interface EnvioInterface {

    Envio registrarEnvio(Envio envio);

    Envio actualizarEstado(Long id, String nuevoEstado);

    List<Envio> buscarPorPedidoId(Long idPedido);

    void eliminarEnvio(Long id);
}
