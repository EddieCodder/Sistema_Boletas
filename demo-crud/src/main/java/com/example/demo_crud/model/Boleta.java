package com.example.demo_crud.model;

import jakarta.persistence.*;
import com.example.demo_crud.repository.ArticuloRepository;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;
    private String direccion;
    private String ruc;
    private String fechaEmision;
    private String fechaVencimiento;
    private String localidad;
    private String guiaRemision;
    private String vendedor;
    private String formaDePago;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boleta")
    private List<DetalleBoleta> detalles = new ArrayList<>();

    private Double descuento;


@Transient // Para que no sea persistido en la base de datos
    private double total;

    @Transient
    private double valorDeVenta;

    @Transient
    private double igv;

    @Transient
    private double precioDeVenta;

    // Inyección del repositorio de artículos
    @Transient
    private ArticuloRepository articuloRepository;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public String getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(String fechaEmision) { this.fechaEmision = fechaEmision; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }
    public String getGuiaRemision() { return guiaRemision; }
    public void setGuiaRemision(String guiaRemision) { this.guiaRemision = guiaRemision; }
    public String getVendedor() { return vendedor; }
    public void setVendedor(String vendedor) { this.vendedor = vendedor; }
    public String getFormaDePago() { return formaDePago; }
    public void setFormaDePago(String formaDePago) { this.formaDePago = formaDePago; }
    public List<DetalleBoleta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleBoleta> detalles) { this.detalles = detalles; }
    public Double getDescuento() { return descuento; }
    public void setDescuento(Double descuento) { this.descuento = descuento; }
    public Double getIgv() { return igv; }
    public void setIgv(Double igv) { this.igv = igv; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public Double getValorDeVenta() { return valorDeVenta; }
    public void setValorDeVenta(Double valorDeVenta) { this.valorDeVenta = valorDeVenta; }
    public Double getPrecioDeVenta() { return precioDeVenta; }
    public void setPrecioDeVenta(Double precioDeVenta) { this.precioDeVenta = precioDeVenta; }

    public void calcularValores(List<Articulo> articulos) {
        double sumaArticulos = 0.0;
        for (DetalleBoleta detalle : detalles) {
            Articulo articulo = articulos.stream()
                                         .filter(a -> a.getId().equals(detalle.getArticuloId()))
                                         .findFirst()
                                         .orElse(null);
            if (articulo != null) {
                sumaArticulos += detalle.getCantidad() * articulo.getValorVentaUnitario();
            }
        }
        this.total = sumaArticulos;
        this.valorDeVenta = this.total - (this.descuento != null ? this.descuento : 0.0);
        this.igv = this.total * 0.18; // Asumiendo un IGV del 18%
        this.precioDeVenta = this.valorDeVenta + this.igv;
    }
    
}
