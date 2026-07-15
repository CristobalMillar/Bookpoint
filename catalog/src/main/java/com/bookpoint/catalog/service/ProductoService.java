package com.bookpoint.catalog.service;

import org.springframework.stereotype.Service;
import com.bookpoint.catalog.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.bookpoint.catalog.model.Producto;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id){
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }
}
