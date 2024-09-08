package com.example.demo_crud.repository;

import com.example.demo_crud.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    @SuppressWarnings("null")
    List<Articulo> findAllById(Iterable<Long> ids);
}
