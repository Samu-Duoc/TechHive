package com.TechHive.Controller;

import com.TechHive.Model.Usuario;
import com.TechHive.Repository.UsuarioR;
import com.TechHive.Service.Interface.UsuarioInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioC {

    @Autowired
    private UsuarioInterface usuarioService;

    @GetMapping
    public List<Usuario> listaUsuarios() {
        return usuarioService.listaUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obternerUsuarioPorId(id);
    }

    @GetMapping("/rut/{rut}")
    public Usuario obtenerUsuarioPorRut(@PathVariable String rut) {
        return usuarioService.obtenerUsuarioPorRut(rut);
    }

    @GetMapping("/correo/{correo}")
    public Usuario obtenerUsuarioPorCorreo(@PathVariable String correo) {
        return usuarioService.obtenerUsuarioPorCorreo(correo);
    }

    @PutMapping("/{id}")
    public Usuario guardUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizUsuario(id, usuario);
    }
    
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
