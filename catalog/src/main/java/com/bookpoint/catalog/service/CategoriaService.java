package com.bookpoint.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookpoint.catalog.repository.CategoriaRepository;
import com.bookpoint.catalog.model.Categoria;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas(){
        return categoriaRepository.findAll();
    }

    public Categoria guardar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}
