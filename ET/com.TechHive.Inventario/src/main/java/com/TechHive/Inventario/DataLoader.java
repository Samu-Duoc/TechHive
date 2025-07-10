package com.TechHive.Inventario;

import com.TechHive.Inventario.Model.Inventario;
import com.TechHive.Inventario.Repository.InventarioRepository;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("dev")
@Component

public class DataLoader implements CommandLineRunner {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Genera 10 registros de inventario con datos aleatorios y realistas
        for (int i = 0; i < 10; i++) {
            Inventario inventario = new Inventario();
            // El id será autogenerado por la base de datos
            inventario.setNombreProducto(faker.commerce().productName());
            inventario.setDescripcion(faker.lorem().sentence(8, 3));
            inventario.setCategoria(faker.commerce().department());
            inventario.setPrecio(faker.number().randomDouble(2, 10, 5000));
            inventario.setStock(faker.number().numberBetween(1, 99999));
            inventarioRepository.save(inventario);
        }
    }

}
