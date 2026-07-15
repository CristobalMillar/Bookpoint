package com.bookpoint.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookpoint.catalog.model.Categoria;
import com.bookpoint.catalog.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categoría", description = "API para la gestión de categorías de productos")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    @Operation(summary = "Listar todas las categorías", description = "Obtiene una lista de todas las categorías")
    public List<Categoria> listarTodas (){
        return categoriaService.listarTodas();
    }

    @PostMapping
    @Operation(summary = "Crear una nueva categoría", description = "Crea una nueva categoría de producto")
    public Categoria crear(@RequestBody Categoria categoria){
        return categoriaService.guardar(categoria);
    }
}
