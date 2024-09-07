package com.example.demo_crud.model;

import jakarta.persistence.*;

@Entity
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unidad;
    private int cantidad;
    private double valorVentaUnitario;

    @Transient
    private double total;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorVentaUnitario() {
        return valorVentaUnitario;
    }

    public void setValorVentaUnitario(double valorVentaUnitario) {
        this.valorVentaUnitario = valorVentaUnitario;
    }

    public double getTotal() {
        return cantidad * valorVentaUnitario;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
