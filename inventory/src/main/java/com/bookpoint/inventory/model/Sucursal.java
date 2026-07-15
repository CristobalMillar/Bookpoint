package com.bookpoint.inventory.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSucursal;

    @Column(nullable = false)
    private String nombre;

    private String direccion;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    @JsonIgnore // Evita bucles infinitos en el JSON
    private List<Inventario> inventarios;

    // Getters y Setters
    public Long getIdSucursal() { return idSucursal; }
    public void setIdSucursal(Long idSucursal) { this.idSucursal = idSucursal; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<Inventario> getInventarios() { return inventarios; }
    public void setInventarios(List<Inventario> inventarios) { this.inventarios = inventarios; }
}