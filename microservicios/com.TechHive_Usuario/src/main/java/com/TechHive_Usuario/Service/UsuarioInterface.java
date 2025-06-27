package com.TechHive_Usuario.Service;

import com.TechHive_Usuario.Model.Usuario;
import java.util.List;


public interface UsuarioInterface {

    List<Usuario> listaUsuarios();

    Usuario obtenerUsuarioPorId(Integer id);

    Usuario obtenerUsuarioPorRut(String rut);

    Usuario buscarPorCorreo(String correo);

    Usuario actualizarUsuario(Integer id, Usuario usuario);

    void eliminarUsuario(Integer id);
}
