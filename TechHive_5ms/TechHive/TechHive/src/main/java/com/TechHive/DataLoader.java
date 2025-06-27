package com.TechHive;

import com.TechHive.Repository.UsuarioR;

import jakarta.annotation.PostConstruct;

import com.TechHive.Model.Usuario;
import com.TechHive.Model.Inventario;
import com.TechHive.Repository.InventarioR;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader {

     // Genera Usuarios tipo cliente
    @Autowired
    private UsuarioR usuarioR;

    @PostConstruct
    public void initUsuarios() {
        Faker faker = new Faker();
        
        // Genera Usuarios tipo cliente
        for (int i = 0; i < 5; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setRut("12345678-" + faker.number().numberBetween(0, 9));

            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setContrasenia(faker.internet().password());

            usuario.setRol("Cliente");
            usuario.setComuna(faker.address().cityName());
            usuario.setDireccion(faker.address().streetAddress());
            usuario.setTelefono("+569" + faker.phoneNumber().phoneNumber());
            usuario.setFechaNacimiento(faker.date().birthday().toString());
            usuarioR.save(usuario);
        }

        // Genera Usuarios tipo administrador
        for (int i = 0; i < 5; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setRut("87654321-" + faker.number().numberBetween(0, 9));
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setContrasenia(faker.internet().password());
            usuario.setRol("Administrador");
            usuario.setComuna(faker.address().cityName());
            usuario.setDireccion(faker.address().streetAddress());
            usuario.setTelefono("+569" + faker.phoneNumber().phoneNumber());
            usuario.setFechaNacimiento(faker.date().birthday().toString());
            usuarioR.save(usuario);
        }
    }

    // Genera un Inventario con productos tecnologicos
    @Autowired
    private InventarioR inventarioR;

    @PostConstruct
    public void initInventario() {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Inventario inventario = new Inventario();
            inventario.setNombreProducto(faker.commerce().productName());
            inventario.setCategoria(faker.commerce().department());
            inventario.setDescripcion(faker.lorem().sentence());
            inventario.setPrecio(Double.parseDouble(faker.commerce().price().
            replace(",", ".")));

            inventario.setStock(faker.number().numberBetween(5, 50));
            inventarioR.save(inventario);
        }
    }
}

