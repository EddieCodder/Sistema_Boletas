package com.example.demo_crud.controller;

import com.example.demo_crud.model.Boleta;
import com.example.demo_crud.services.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public List<Boleta> getAllBoletas() {
        return boletaService.getAllBoletas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> getBoletaById(@PathVariable Long id) {
        Optional<Boleta> boleta = boletaService.getBoletaById(id);
        return boleta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Boleta> createBoleta(@RequestBody Boleta boleta) {
        Boleta savedBoleta = boletaService.saveBoleta(boleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBoleta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boleta> updateBoleta(@PathVariable Long id, @RequestBody Boleta boleta) {
        if (!boletaService.getBoletaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boleta.setId(id);
        Boleta updatedBoleta = boletaService.saveBoleta(boleta);
        return ResponseEntity.ok(updatedBoleta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoleta(@PathVariable Long id) {
        if (!boletaService.getBoletaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boletaService.deleteBoleta(id);
        return ResponseEntity.noContent().build();
    }
}
