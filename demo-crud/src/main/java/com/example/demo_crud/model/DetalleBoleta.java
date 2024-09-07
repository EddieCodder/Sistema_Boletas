package com.example.demo_crud.model;

/*import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo_crud.services.ArticuloService;*/

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class DetalleBoleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boleta_id")
    @JsonIgnore
    private Boleta boleta;    
    private Long articuloId;
    private Integer cantidad;
    
    /* 
    @Transient
    private String articuloNombre;
    @Transient
    private String unidad;
    @Transient
    private Double valorVentaUnitario;

    @Autowired
    @Transient
    private ArticuloService articuloService; // Inyección de servicio
*/  
    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Boleta getBoleta() { return boleta; }
    public void setBoleta(Boleta boleta) { this.boleta = boleta; }
    public Long getArticuloId() { return articuloId; }
    public void setArticuloId(Long articuloId) { 
        this.articuloId = articuloId;
        //updateArticuloDetails(articuloId); // Actualiza los detalles del artículo
    }    
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
   
   
   /* 
    public String getArticuloNombre() { return articuloNombre; }
    public void setArticuloNombre(String articuloNombre) { this.articuloNombre = articuloNombre; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Double getValorVentaUnitario() { return valorVentaUnitario; }
    public void setValorVentaUnitario(Double valorVentaUnitario) { this.valorVentaUnitario = valorVentaUnitario; }
 
    // Método para actualizar los detalles del artículo
    private void updateArticuloDetails(Long articuloId) {
        if (articuloId != null) {
            Articulo articulo = articuloService.findArticuloById(articuloId).orElse(null);
            if (articulo != null) {
                this.articuloNombre = articulo.getNombre();
                this.unidad = articulo.getUnidad();
                this.valorVentaUnitario = articulo.getValorVentaUnitario();
            }
        }
    }*/

}
