package com.TechHive_Soporte.Service;

import com.TechHive_Soporte.Model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketInterface {

    Ticket crearTicket(Ticket ticket);

    List<Ticket> ObtenerTodosLosTickets();

    Optional<Ticket> ObtenerTicketPorId(Long id);

    Ticket actualizarTicket(Long id, Ticket ticket);

    void eliminarTicket(Long id);

}
