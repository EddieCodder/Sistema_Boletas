package com.example.demo_crud.model;

import jakarta.persistence.*;

@Entity
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String unidad;
    private Double valorVentaUnitario;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Double getValorVentaUnitario() { return valorVentaUnitario; }
    public void setValorVentaUnitario(Double valorVentaUnitario) { this.valorVentaUnitario = valorVentaUnitario; }
}
