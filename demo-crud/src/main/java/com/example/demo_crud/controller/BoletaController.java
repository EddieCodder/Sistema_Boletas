package com.example.demo_crud.controller;

import com.example.demo_crud.model.Boleta;
import com.example.demo_crud.model.DetalleBoleta;
import com.example.demo_crud.model.Articulo;
import com.example.demo_crud.repository.BoletaRepository;
import com.example.demo_crud.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @PostMapping
    public Boleta createBoleta(@RequestBody Boleta boleta) {
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
                detalle.setArticuloNombre(articulo.getNombre());
                detalle.setUnidad(articulo.getUnidad());
                detalle.setValorVentaUnitario(articulo.getValorVentaUnitario());
            } else {
                throw new RuntimeException("Artículo no encontrado con ID: " + detalle.getArticuloId());
            }
        }
        boleta.calcularValores(articulos);
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
                    detalle.setArticuloNombre(articulo.getNombre());
                    detalle.setUnidad(articulo.getUnidad());
                    detalle.setValorVentaUnitario(articulo.getValorVentaUnitario());
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
        return boletaRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Boleta> getAllBoletas() {
        return boletaRepository.findAll();
    }
}
