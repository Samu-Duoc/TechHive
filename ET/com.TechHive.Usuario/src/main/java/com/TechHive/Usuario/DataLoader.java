package com.TechHive.Usuario;

import com.TechHive.Usuario.Model.Usuarios;
import com.TechHive.Usuario.Repository.UsuarioRepository;
import net.datafaker.Faker;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Genera 10 registros de usuarios con datos aleatorios
        for (int i = 0; i < 10; i++) {
            Usuarios usuario = new Usuarios();
            usuario.setId((long) (i + 1)); // Usar Long si es necesario
            usuario.setRut(faker.idNumber().valid());
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setPassword(generateRandomPassword(faker)); // Contraseña aleatoria
            usuario.setTelefono(faker.phoneNumber().cellPhone());
            usuario.setDireccion(faker.address().streetAddress());
            usuario.setRol(faker.options().option("cliente", "admin", "vendedor")); // Rol aleatorio
            
            usuarioRepository.save(usuario);
        }
    }
    
    // Método para generar contraseña aleatoria
    private String generateRandomPassword(Faker faker) {
        return faker.internet().password(8, 16, true, true, true);
    }
}
