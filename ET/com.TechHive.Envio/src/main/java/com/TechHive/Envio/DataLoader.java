package com.TechHive.Envio;

import com.TechHive.Envio.Model.Envio;
import com.TechHive.Envio.Repository.EnvioRepository;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("dev")
@Component

public class DataLoader implements CommandLineRunner {

    @Autowired
    private EnvioRepository envioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Genera 10 registros de envíos con datos aleatorios
        for (int i = 0; i < 10; i++) {
            Envio envio = new Envio();
            envio.setId((long) (i + 1));
            envio.setDestinatario(faker.name().fullName());
            envio.setDireccion(faker.address().streetAddress());
            envio.setCiudad(faker.address().city());
            envio.setEstado(faker.options().option("En ruta", "Entregado", "Pendiente"));
            envioRepository.save(envio);
        }
    }
}
