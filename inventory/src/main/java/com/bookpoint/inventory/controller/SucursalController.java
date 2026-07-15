package com.bookpoint.inventory.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bookpoint.inventory.model.Sucursal;
import com.bookpoint.inventory.service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/sucursales")
@Tag(name = "Sucursal", description = "API para la gestión de sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    @Operation(summary = "Listar todas las sucursales")
    public List<Sucursal> listarTodas() {
        return sucursalService.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener sucursal por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    public ResponseEntity<Sucursal> obtenerPorId(@PathVariable Long id) {
        return sucursalService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sucursal")
    @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente")
    public Sucursal crear(@RequestBody Sucursal sucursal) { // Import correcto de Spring
        return sucursalService.guardar(sucursal);
    }
}