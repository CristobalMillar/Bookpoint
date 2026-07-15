package com.bookpoint.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookpoint.inventory.model.Sucursal;
import com.bookpoint.inventory.repository.SucursalRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> listarTodas() {
        return sucursalRepository.findAll();
    }

    public Optional<Sucursal> obtenerPorId(Long id) {
        return sucursalRepository.findById(id);
    }

    public Sucursal guardar(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }
}