package com.TechHive_Usuario.Service;

import com.TechHive_Usuario.Model.Usuario;
import com.TechHive_Usuario.Repository.UsuarioR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UsuarioService implements UsuarioInterface {

    @Autowired
    private UsuarioR  usuarioR;

    @Override
    public List<Usuario> listaUsuarios() {
        return usuarioR.findAll();
    }
    @Override
    public Usuario obtenerUsuarioPorRut(String rut) {
        return usuarioR.findByRut(rut).orElse(null);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioR.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return usuarioR.findByCorreo(correo).orElse(null);
    }

    @Override
    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        Usuario existente = obtenerUsuarioPorId(id);

        existente.setNombres(usuario.getNombres());
        existente.setApellidos(usuario.getApellidos());
        existente.setCorreo(usuario.getCorreo());
        existente.setDireccion(usuario.getDireccion());
        existente.setRol(usuario.getRol()); // opcional si es admin

        return usuarioR.save(existente);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioR.deleteById(id);
    }
}
