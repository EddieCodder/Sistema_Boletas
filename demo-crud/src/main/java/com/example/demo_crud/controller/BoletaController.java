package com.example.demo_crud.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_crud.model.Articulo;
import com.example.demo_crud.model.Boleta;
import com.example.demo_crud.model.DetalleBoleta;
import com.example.demo_crud.repository.ArticuloRepository;
import com.example.demo_crud.repository.BoletaRepository;

@RestController
@RequestMapping("/api/boletas")
@CrossOrigin("*")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @PostMapping
    public Boleta createBoleta(@RequestBody Boleta boleta) {
        // Obtener los artículos asociados a los detalles de la boleta
        List<Articulo> articulos = articuloRepository.findAllById(
            boleta.getDetalles().stream()
                  .map(DetalleBoleta::getArticuloId)  // Obtener los IDs de los artículos
                  .collect(Collectors.toList())
        );
    
        // Asignar la información de los artículos a los detalles de la boleta
        for (DetalleBoleta detalle : boleta.getDetalles()) {
            Articulo articulo = articulos.stream()
                                         .filter(a -> a.getId().equals(detalle.getArticuloId()))
                                         .findFirst()
                                         .orElse(null);
    
            if (articulo != null) {
                detalle.setBoleta(boleta);
                /*detalle.setArticuloNombre(articulo.getNombre());
                detalle.setUnidad(articulo.getUnidad());
                detalle.setValorVentaUnitario(articulo.getValorVentaUnitario());*/
            } else {
                throw new RuntimeException("Artículo no encontrado con ID: " + detalle.getArticuloId());
            }
        }
    
        // Calcular los valores de la boleta (total, valor de venta, IGV, etc.)
        boleta.calcularValores(articulos);
    
        // Guardar la boleta con sus detalles
        return boletaRepository.save(boleta);
    }
    

    @PutMapping("/{id}")
    public Boleta updateBoleta(@PathVariable Long id, @RequestBody Boleta boleta) {
        if (boletaRepository.existsById(id)) {
            boleta.setId(id);

            // Obtener los artículos necesarios
            List<Articulo> articulos = articuloRepository.findAllById(
                boleta.getDetalles().stream()
                      .map(DetalleBoleta::getArticuloId)
                      .collect(Collectors.toList())
            );

            for (DetalleBoleta detalle : boleta.getDetalles()) {
                Articulo articulo = articulos.stream()
                                             .filter(a -> a.getId().equals(detalle.getArticuloId()))
                                             .findFirst()
                                             .orElse(null);
                if (articulo != null) {
                    detalle.setBoleta(boleta);
                    /*detalle.setArticuloNombre(articulo.getNombre());
                    detalle.setUnidad(articulo.getUnidad());
                    detalle.setValorVentaUnitario(articulo.getValorVentaUnitario());*/
                } else {
                    throw new RuntimeException("Artículo no encontrado con ID: " + detalle.getArticuloId());
                }
            }

            boleta.calcularValores(articulos);
            return boletaRepository.save(boleta);
        } else {
            throw new RuntimeException("Boleta no encontrada con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBoleta(@PathVariable Long id) {
        
        boletaRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Boleta getBoleta(@PathVariable Long id) {
        Boleta boleta = boletaRepository.findById(id).orElse(null);

        if (boleta != null) {
            // Cargar los artículos asociados para recalcular los valores
            List<Articulo> articulos = articuloRepository.findAllById(
                boleta.getDetalles().stream()
                      .map(DetalleBoleta::getArticuloId)
                      .collect(Collectors.toList())
            );

            // Recalcular los valores de la boleta
            boleta.calcularValores(articulos);
        }

        return boleta;  // Devolver la boleta con los valores actualizados
    }

    @GetMapping
    public List<Boleta> getAllBoletas() {
        List<Boleta> boletas = boletaRepository.findAll();

        for (Boleta boleta : boletas) {
            // Cargar los artículos asociados para recalcular los valores
            List<Articulo> articulos = articuloRepository.findAllById(
                boleta.getDetalles().stream()
                      .map(DetalleBoleta::getArticuloId)
                      .collect(Collectors.toList())
            );

            // Recalcular los valores de la boleta
            boleta.calcularValores(articulos);
        }

        return boletas;  // Devolver la lista de boletas con los valores actualizados
    }
}