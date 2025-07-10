package com.TechHive.Envio.Service;

import com.TechHive.Envio.Model.Envio;
import com.TechHive.Envio.Repository.EnvioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> findAll() {
        return envioRepository.findAll();
    }

    public Envio findById(Long id) {
        return envioRepository.findById(id).orElse(null);
    }

    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    public void deleteById(Long id) {
        envioRepository.deleteById(id);
    }

    public List<Envio> findByEstado(String estado) {
        return envioRepository.findByEstado(estado);
    }

    public Envio update(Long id, Envio envioActualizado) {
        Envio envio = envioRepository.findById(id).orElse(null);
        if (envio != null) {
            envio.setDestinatario(envioActualizado.getDestinatario());
            envio.setDireccion(envioActualizado.getDireccion());
            envio.setCiudad(envioActualizado.getCiudad());
            envio.setEstado(envioActualizado.getEstado());
            return envioRepository.save(envio);
        }
        return null;
    }

    /**
     * Actualiza un envío existente usando el objeto Envio (debe tener ID).
     * @param envioActualizado Envio con los datos actualizados.
     * @return el Envio actualizado, o null si no existe.
     */
    public Envio update(Envio envioActualizado) {
        if (envioActualizado == null || envioActualizado.getId() == null) return null;
        return update(envioActualizado.getId(), envioActualizado);
    }

}
