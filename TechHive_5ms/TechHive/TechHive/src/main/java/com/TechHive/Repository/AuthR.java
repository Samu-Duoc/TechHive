package com.TechHive.Repository;

import com.TechHive.Model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthR extends JpaRepository <Auth, Long> {
Optional<Auth> findByRut(String rut);
Optional<Auth> findByCorreo(String correo);
}


