package com.TechHive.Inventario;

import com.TechHive.Inventario.Model.Inventario;
import com.TechHive.Inventario.Repository.InventarioRepository;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("test")
@Component

public class DataLoader implements CommandLineRunner {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🔄 DataLoader ejecutándose - Cargando datos de prueba...");
        
        Faker faker = new Faker();

        // Genera 10 registros de inventario con datos aleatorios y realistas
        for (int i = 0; i < 10; i++) {
            Inventario inventario = new Inventario();
            // El id será autogenerado por la base de datos
            inventario.setNombreProducto(faker.commerce().productName());
            inventario.setDescripcion(faker.lorem().sentence(8, 3));
            inventario.setCategoria(faker.commerce().department());
            inventario.setPrecio(faker.number().randomDouble(2, 10, 5000));
            inventario.setStock(faker.number().numberBetween(1, 100)); // Ajustado al rango de validación
            inventarioRepository.save(inventario);
        }
        
        System.out.println("✅ DataLoader completado - 10 productos agregados al inventario");
    }

}
