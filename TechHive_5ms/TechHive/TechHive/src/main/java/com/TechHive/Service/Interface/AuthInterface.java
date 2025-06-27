package com.TechHive.Service.Interface;

import com.TechHive.Model.Auth;
import java.util.List;
import java.util.Optional;

public interface AuthInterface {

    List<Auth> listarUsuarios();
    Auth obtenerUsuarioPorId(Long id);
    Auth obtenerUsuarioPorRut(String rut);
    Auth obtenerUsuarioPorCorreo(String correo);
    Auth guardarUsuario(Auth usuario);
    Auth actualizarUsuario(Long id, Auth usuario);
    void eliminarUsuario(Long id);

    // Métodos específicos para autenticación
    Optional<Auth> login(String correo, String contrasenia);
}









