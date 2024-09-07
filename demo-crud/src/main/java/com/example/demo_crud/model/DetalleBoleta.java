package com.example.demo_crud.model;

import jakarta.persistence.*;

@Entity
public class DetalleBoleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boleta_id")
    private Boleta boleta;

    private Long articuloId;
    private Integer cantidad;
    
    @Transient
    private String articuloNombre;
    @Transient
    private String unidad;
    @Transient
    private Double valorVentaUnitario;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Boleta getBoleta() { return boleta; }
    public void setBoleta(Boleta boleta) { this.boleta = boleta; }
    public Long getArticuloId() { return articuloId; }
    public void setArticuloId(Long articuloId) { this.articuloId = articuloId; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public String getArticuloNombre() { return articuloNombre; }
    public void setArticuloNombre(String articuloNombre) { this.articuloNombre = articuloNombre; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Double getValorVentaUnitario() { return valorVentaUnitario; }
    public void setValorVentaUnitario(Double valorVentaUnitario) { this.valorVentaUnitario = valorVentaUnitario; }
}
