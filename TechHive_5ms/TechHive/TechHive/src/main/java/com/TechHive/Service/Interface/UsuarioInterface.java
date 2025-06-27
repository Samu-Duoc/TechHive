package com.TechHive.Service.Interface;

import com.TechHive.Model.Usuario;

import java.util.List;

public interface UsuarioInterface {

    List <Usuario> listaUsuarios();
    Usuario obternerUsuarioPorId(Long id);
    Usuario obtenerUsuarioPorRut(String rut);
    Usuario obtenerUsuarioPorCorreo(String correo);
    Usuario actualizUsuario(Long id, Usuario usuario);

    void eliminarUsuario(Long id);

}
