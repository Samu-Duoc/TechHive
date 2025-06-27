package com.TechHive.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;



public interface Pedido extends JpaRepository<Pedido, Long> {

}
