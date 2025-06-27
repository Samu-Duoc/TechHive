package com.TechHive.Service.ServiceClass;

import com.TechHive.Model.Usuario;
import com.TechHive.Service.Interface.UsuarioInterface;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.TechHive.Repository.UsuarioR;


import java.util.List;

@Service

public class UsuarioService implements UsuarioInterface {

    @Autowired
    private UsuarioR usuarioR;

    @Override
    public List<Usuario> listaUsuarios() {
        return usuarioR.findAll();
    }

    @Override
    public Usuario obternerUsuarioPorId(Long id) {
        return usuarioR.findById(id).orElse(null);
    }

    @Override
    public Usuario obtenerUsuarioPorRut(String rut) {
        return usuarioR.findByRUT(rut).orElse(null);
    }

    @Override
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioR.findByCorreo(correo).orElse(null);
    }

    @Override
    public Usuario actualizUsuario(Long id, Usuario usuario) {
        if (usuarioR.existsById(id)) {
            usuario.setId(id);
            return usuarioR.save(usuario);
        }
        return null;
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioR.deleteById(id);
    }

}
