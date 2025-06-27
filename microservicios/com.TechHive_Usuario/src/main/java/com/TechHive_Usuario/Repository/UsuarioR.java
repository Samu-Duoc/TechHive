package com.TechHive_Usuario.Repository;

import com.TechHive_Usuario.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioR extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByRut(String rut);
    Optional<Usuario> findByCorreo(String correo);
}
