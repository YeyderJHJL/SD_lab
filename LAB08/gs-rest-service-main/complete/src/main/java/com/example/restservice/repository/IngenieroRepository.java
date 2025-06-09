package com.example.restservice.repository;

import com.example.restservice.model.Ingeniero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngenieroRepository extends JpaRepository<Ingeniero, Integer> {
    
}