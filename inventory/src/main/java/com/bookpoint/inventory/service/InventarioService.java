package com.bookpoint.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookpoint.inventory.model.Inventario;
import com.bookpoint.inventory.repository.InventarioRepository;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> listarTodos() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> obtenerPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    public List<Inventario> obtenerPorProducto(Long idProducto) {
        return inventarioRepository.findByIdProducto(idProducto);
    }

    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }
}