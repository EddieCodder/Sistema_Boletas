package com.example.demo_crud.controller;

import com.example.demo_crud.model.Articulo;
import com.example.demo_crud.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloRepository articuloRepository;

    @GetMapping
    public List<Articulo> getAllArticulos() {
        return articuloRepository.findAll();
    }

    @PostMapping
    public Articulo createArticulo(@RequestBody Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @GetMapping("/{id}")
    public Articulo getArticuloById(@PathVariable Long id) {
        return articuloRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Articulo updateArticulo(@PathVariable Long id, @RequestBody Articulo articuloDetails) {
        Articulo articulo = articuloRepository.findById(id).orElseThrow();

        articulo.setNombre(articuloDetails.getNombre());
        articulo.setUnidad(articuloDetails.getUnidad());
        articulo.setValorVentaUnitario(articuloDetails.getValorVentaUnitario());

        return articuloRepository.save(articulo);
    }

    @DeleteMapping("/{id}")
    public void deleteArticulo(@PathVariable Long id) {
        Articulo articulo = articuloRepository.findById(id).orElseThrow();
        articuloRepository.delete(articulo);
    }
}
