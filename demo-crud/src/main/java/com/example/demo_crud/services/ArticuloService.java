package com.example.demo_crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_crud.model.Articulo;
import com.example.demo_crud.repository.ArticuloRepository;

import java.util.Optional;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    public Optional<Articulo> findArticuloById(Long id) {
        return articuloRepository.findById(id);
    }
}

