package com.TechHive.Usuario.Controller;

import com.TechHive.Usuario.Model.Usuarios;
import com.TechHive.Usuario.Service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public List<Usuarios> getAllUsuarios() {
        return usuariosService.findAll();
    }

    @GetMapping("/{id}")
    public Usuarios getUsuarioById(@PathVariable Long id) {
        return usuariosService.findById(id);
    }
    
    @PostMapping
    public Usuarios createUsuario(@RequestBody Usuarios usuario) {
        return usuariosService.save(usuario);
    }
    
    @PutMapping("/{id}")
    public Usuarios updateUsuario(@PathVariable Long id, @RequestBody Usuarios usuario) {
        usuario.setId(id);
        return usuariosService.save(usuario);
        
    
    }
}
