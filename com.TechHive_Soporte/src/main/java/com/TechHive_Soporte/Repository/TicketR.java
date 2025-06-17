package com.TechHive_Soporte.Repository;

import com.TechHive_Soporte.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketR extends JpaRepository<Ticket, Long>{

}
