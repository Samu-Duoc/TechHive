package com.TechHive_Pago.Service;

import com.TechHive_Pago.Model.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoInterface {

    Pago registrarPago(Pago pago);

    List<Pago> listarPagos();

    Optional<Pago> obtenerPagoPorId(Long id);

    List<Pago> obtenerPorIdPedido(Long idPedido);

    void eliminarPago(Long id);
}
