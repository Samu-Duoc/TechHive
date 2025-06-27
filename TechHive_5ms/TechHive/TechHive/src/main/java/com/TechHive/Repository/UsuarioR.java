package com.TechHive.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TechHive.Model.Usuario;

import java.util.Optional;

public interface UsuarioR extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByRUT(String rut);
    Optional<Usuario> findByCorreo(String correo);
}
