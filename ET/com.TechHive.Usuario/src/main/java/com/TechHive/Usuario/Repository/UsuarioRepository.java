package com.TechHive.Usuario.Repository;

import com.TechHive.Usuario.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    
}
