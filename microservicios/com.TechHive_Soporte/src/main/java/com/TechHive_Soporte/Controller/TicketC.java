package com.TechHive_Soporte.Controller;

import com.TechHive_Soporte.Model.Ticket;
import com.TechHive_Soporte.Service.TicketS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soporte")
@CrossOrigin(origins = "*")


public class TicketC {

    @Autowired
    private TicketS ticketS;

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketS.crearTicket(ticket));
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> obtenerTodos() {
        return ResponseEntity.ok(ticketS.ObtenerTodosLosTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerPorId(@PathVariable Long id) {
        return ticketS.ObtenerTicketPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizar(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket actualizado = ticketS.actualizarTicket(id, ticket);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ticketS.eliminarTicket(id);
        return ResponseEntity.noContent().build();
    }

}