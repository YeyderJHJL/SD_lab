package com.example.restservice.service;

import com.example.restservice.model.Ingeniero;
import com.example.restservice.repository.IngenieroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngenieroService {

    private final IngenieroRepository ingenieroRepository;

    public IngenieroService(IngenieroRepository ingenieroRepository) {
        this.ingenieroRepository = ingenieroRepository;
    }

    public List<Ingeniero> findAll() {
        return ingenieroRepository.findAll();
    }

    public Optional<Ingeniero> findById(Integer id) {
        return ingenieroRepository.findById(id);
    }

    public Ingeniero save(Ingeniero ingeniero) {
        return ingenieroRepository.save(ingeniero);
    }

    public void deleteById(Integer id) {
        ingenieroRepository.deleteById(id);
    }
}
