package com.TechHive.Envio.Service;

import com.TechHive.Envio.Model.Envio;
import com.TechHive.Envio.Repository.EnvioRepository;

import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class EnvioServiceTests {

    @Autowired
    private EnvioService envioService;

    @MockBean
    private EnvioRepository envioRepository;

    /**
     * Prueba que el método findAll retorne todos los envíos.
     */
    @Test
    public void testFindAll() {
        Envio envio = createEnvio();
        when(envioRepository.findAll()).thenReturn(List.of(envio));
        List<Envio> envios = envioService.findAll();
        assertNotNull(envios);
        assertEquals(1, envios.size());
        assertEquals(envio.getId(), envios.get(0).getId());
    }

    /**
     * Prueba que el método findById retorne el envío correcto por ID.
     */
    @Test
    public void testFindById() {
        Long id = 1L;
        Envio envio = createEnvio();
        when(envioRepository.findById(id)).thenReturn(Optional.of(envio));
        Envio foundEnvio = envioService.findById(id);
        assertNotNull(foundEnvio);
        assertEquals(envio.getId(), foundEnvio.getId());
    }

    /**
     * Prueba que el método save guarde correctamente un envío.
     */
    @Test
    public void testSave() {
        Envio envio = createEnvio();
        when(envioRepository.save(envio)).thenReturn(envio);
        Envio savedEnvio = envioService.save(envio);
        assertNotNull(savedEnvio);
        assertEquals("En ruta", savedEnvio.getEstado());
    }

    /**
     * Prueba que el método save permita actualizar el estado de un envío (forma tradicional).
     */
    @Test
    public void testUpdate() {
        Envio envio = createEnvio();
        envio.setEstado("Entregado");
        when(envioRepository.save(envio)).thenReturn(envio);
        Envio updatedEnvio = envioService.save(envio);
        assertNotNull(updatedEnvio);
        assertEquals("Entregado", updatedEnvio.getEstado());
    }

    //Prueba que el método deleteById elimine el envío por ID.
    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(envioRepository).deleteById(id);
        envioService.deleteById(id);
        verify(envioRepository, times(1)).deleteById(id);
    }

   // Prueba que el método findByEstado retorne los envíos con el estado especificado.
    @Test
    public void testFindByEstado() {
        Envio envio = createEnvio();
        when(envioRepository.findByEstado("En ruta")).thenReturn(List.of(envio));
        List<Envio> envios = envioService.findByEstado("En ruta");
        assertNotNull(envios);
        assertFalse(envios.isEmpty());
        assertEquals("En ruta", envios.get(0).getEstado());
    }

    
     //Prueba que el método update del servicio actualice el estado de un envío.
    @Test
    public void testUpdateEstado() {
        Long id = 1L;
        Envio envio = createEnvio();
        Envio envioActualizado = createEnvio();
        envioActualizado.setEstado("Entregado");
        
        // Mock para findById
        when(envioRepository.findById(id)).thenReturn(Optional.of(envio));
        // Mock para save
        when(envioRepository.save(any(Envio.class))).thenReturn(envioActualizado);
        
        // El método update debe estar implementado en EnvioService
        Envio updatedEnvio = envioService.update(envioActualizado);
        assertNotNull(updatedEnvio);
        assertEquals("Entregado", updatedEnvio.getEstado());
        assertEquals(id, updatedEnvio.getId());
    }

    private Envio createEnvio() {
        Envio envio = new Envio();
        envio.setId(1L);
        envio.setDestinatario("Juan Perez");
        envio.setDireccion("Calle Falsa 123");
        envio.setCiudad("Ciudad Ficticia");
        envio.setEstado("En ruta");
        return envio;
    }
}
