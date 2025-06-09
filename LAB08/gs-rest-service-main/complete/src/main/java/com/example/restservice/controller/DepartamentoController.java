package com.example.restservice.controller;

import com.example.restservice.model.Departamento;
import com.example.restservice.service.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List<Departamento> getAllDepartamentos() {
        return departamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable Integer id) {
        return departamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoService.save(departamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable Integer id, @RequestBody Departamento departamentoDetails) {
        return departamentoService.findById(id).map(departamento -> {
            departamento.setNombre(departamentoDetails.getNombre());
            departamento.setTelefono(departamentoDetails.getTelefono());
            departamento.setFax(departamentoDetails.getFax());
            return ResponseEntity.ok(departamentoService.save(departamento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Integer id) {
        if (departamentoService.findById(id).isPresent()) {
            departamentoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}