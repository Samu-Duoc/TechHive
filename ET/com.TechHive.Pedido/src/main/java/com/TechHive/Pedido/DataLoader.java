package com.TechHive.Pedido;

import com.TechHive.Pedido.Model.Pedido;
import com.TechHive.Pedido.Service.PedidoService;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PedidoService pedidoService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Genera 10 registros de pedidos con datos aleatorios y realistas
        for (int i = 0; i < 10; i++) {
            Pedido pedido = new Pedido();
            // El id será autogenerado por la base de datos
            pedido.setNumeroPedido(faker.number().digits(8));
            pedido.setRutCliente(faker.idNumber().valid());
            pedido.setNombreVendedor(faker.name().fullName());
            pedido.setNombreCliente(faker.name().fullName());
            pedido.setDireccionEntrega(faker.address().fullAddress());
            pedido.setTelefono(faker.phoneNumber().cellPhone());
            pedido.setFechaPedido(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            pedido.setEstado(faker.options().option("Pendiente", "Enviado", "Entregado"));
            pedido.setMetodoPago(faker.options().option("Efectivo", "Tarjeta", "Transferencia"));
            pedido.setDetallesPedido(faker.commerce().productName() + " x" + faker.number().numberBetween(1, 5));
            pedido.setTotalPedido(faker.number().randomDouble(2, 10, 500));
            pedidoService.createPedido(pedido);
        }
    }

}
