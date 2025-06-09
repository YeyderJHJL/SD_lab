package com.example.restservice.controller;

import com.example.restservice.model.Ingeniero;
import com.example.restservice.service.IngenieroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingenieros")
public class IngenieroController {

    private final IngenieroService ingenieroService;

    public IngenieroController(IngenieroService ingenieroService) {
        this.ingenieroService = ingenieroService;
    }

    @GetMapping
    public List<Ingeniero> getAll() {
        return ingenieroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingeniero> getById(@PathVariable Integer id) {
        return ingenieroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ingeniero create(@RequestBody Ingeniero ingeniero) {
        return ingenieroService.save(ingeniero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingeniero> update(@PathVariable Integer id, @RequestBody Ingeniero ingeniero) {
        return ingenieroService.findById(id)
                .map(existing -> {
                    ingeniero.setIdIng(id);
                    Ingeniero updated = ingenieroService.save(ingeniero);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (ingenieroService.findById(id).isPresent()) {
            ingenieroService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}