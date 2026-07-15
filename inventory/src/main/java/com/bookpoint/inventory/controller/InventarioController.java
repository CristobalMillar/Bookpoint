package com.bookpoint.inventory.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bookpoint.inventory.model.Inventario;
import com.bookpoint.inventory.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/inventarios")
@Tag(name = "Inventario", description = "API para la gestión del inventario de productos en sucursales")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Listar todo el inventario")
    public List<Inventario> listarTodos() {
        return inventarioService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener inventario por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable Long id) {
        return inventarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{idProducto}")
    @Operation(summary = "Listar inventarios por ID de Producto")
    public List<Inventario> obtenerPorProducto(@PathVariable Long idProducto) {
        return inventarioService.obtenerPorProducto(idProducto);
    }

    @PostMapping
    @Operation(summary = "Crear o actualizar inventario")
    @ApiResponse(responseCode = "201", description = "Inventario registrado")
    public Inventario crear(@RequestBody Inventario inventario) { // Import correcto de Spring
        return inventarioService.guardar(inventario);
    }
}