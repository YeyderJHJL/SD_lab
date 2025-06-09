package com.example.restservice.repository;

import com.example.restservice.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    // List<Proyecto> findByIdDpto(Integer idDpto);
}