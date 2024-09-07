
package com.example.demo_crud.services;

import com.example.demo_crud.model.Boleta;
import com.example.demo_crud.repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    public List<Boleta> getAllBoletas() {
        return boletaRepository.findAll();
    }

    public Optional<Boleta> getBoletaById(Long id) {
        return boletaRepository.findById(id);
    }

    public Boleta saveBoleta(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    public void deleteBoleta(Long id) {
        boletaRepository.deleteById(id);
    }
}
