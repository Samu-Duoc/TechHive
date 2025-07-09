package com.TechHive.Auth;

import com.TechHive.Auth.Model.Auth;
import com.TechHive.Auth.Repository.AuthRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        

        // Genera 5 registros de usuarios Auth con datos aleatorios
        for (int i = 0; i < 10; i++) {
            Auth auth = new Auth();
            auth.setId((long) (i + 1));
            auth.setCorreo(faker.internet().emailAddress());
            auth.setPassword("password123");
            auth.setRol(faker.options().option("CLIENTE", "ADMINISTRADOR", "VENDEDOR")); // Rol aleatorio
            authRepository.save(auth);
        }
    }
}
