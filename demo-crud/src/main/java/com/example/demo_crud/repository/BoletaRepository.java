package com.example.demo_crud.repository;

import com.example.demo_crud.model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletaRepository extends JpaRepository<Boleta, Long> {
}
