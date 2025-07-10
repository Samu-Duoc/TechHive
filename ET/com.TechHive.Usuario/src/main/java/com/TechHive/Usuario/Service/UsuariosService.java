package com.TechHive.Usuario.Service;

import  com.TechHive.Usuario.Model.Usuarios;
import com.TechHive.Usuario.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuarios> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuarios findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuarios save(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
