package com.TechHive.Inventario.Service;

import org.springframework.stereotype.Service;
import com.TechHive.Inventario.Model.Inventario;
import com.TechHive.Inventario.Repository.InventarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    public Inventario findById(Long id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public void deleteById(Long id) {
        inventarioRepository.deleteById(id);
    }

    /**
     * Actualiza el stock de un producto en inventario.
     * @param id ID del producto
     * @param nuevoStock Nuevo valor de stock
     * @return Inventario actualizado o null si no existe
     */
    public Inventario updateStock(Long id, int nuevoStock) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);
        if (inventario != null) {
            inventario.setStock(nuevoStock);
            return inventarioRepository.save(inventario);
        }
        return null;
    }

}
