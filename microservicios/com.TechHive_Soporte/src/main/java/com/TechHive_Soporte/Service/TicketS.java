package com.TechHive_Soporte.Service;

import com.TechHive_Soporte.Model.Ticket;
import com.TechHive_Soporte.Repository.TicketR;
import com.TechHive_Soporte.Service.TicketInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TicketS  implements TicketInterface {

    @Autowired
    private TicketR ticketR;

    @Override
    public Ticket crearTicket(Ticket ticket) {
        return ticketR.save(ticket);
    }

    @Override
    public List<Ticket> ObtenerTodosLosTickets() {
        return ticketR.findAll();
    }

    @Override
    public Optional <Ticket> ObtenerTicketPorId(Long id) {
        return ticketR.findById(id);
    }

    @Override
    public Ticket actualizarTicket(Long id, Ticket ticketActualizado) {
        return ticketR.findById(id)
                .map(t ->{
                t.setTipo(ticketActualizado.getTipo());
                t.setDescripcion(ticketActualizado.getDescripcion());
                t.setEstado(ticketActualizado.getEstado());
                t.setCorreo(ticketActualizado.getCorreo());
                return ticketR.save(t);
                }).orElse(null);
    }
    @Override
    public void eliminarTicket(Long id) {
        ticketR.deleteById(id);
    }
}
