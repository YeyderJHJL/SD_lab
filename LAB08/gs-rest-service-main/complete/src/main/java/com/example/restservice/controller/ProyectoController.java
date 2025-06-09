package com.example.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.restservice.model.Proyecto;
import com.example.restservice.service.ProyectoService;
import com.example.restservice.service.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    @Autowired
    private DepartamentoService departamentoService;

    public ProyectoController(ProyectoService proyectoService, DepartamentoService departamentoService) {
        this.proyectoService = proyectoService;
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List<Proyecto> getAllProyectos() {
        return proyectoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getProyectoById(@PathVariable Integer id) {
        return proyectoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proyecto createProyecto(@RequestBody Proyecto proyecto) {
        return proyectoService.save(proyecto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> updateProyecto(@PathVariable Integer id, @RequestBody Proyecto proyectoDetails) {
        return proyectoService.findById(id).map(proyecto -> {
            proyecto.setNombre(proyectoDetails.getNombre());
            proyecto.setFecInicio(proyectoDetails.getFecInicio());
            proyecto.setFecTermino(proyectoDetails.getFecTermino());
            
            Integer idDpto = proyectoDetails.getDepartamento().getIdDpto();
            departamentoService.findById(idDpto).ifPresent(proyecto::setDepartamento);

            return ResponseEntity.ok(proyectoService.save(proyecto));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer id) {
        if (proyectoService.findById(id).isPresent()) {
            proyectoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /*@GetMapping("/departamento/{idDpto}")
    public List<Proyecto> getProyectosByDepartamento(@PathVariable Integer idDpto) {
        return proyectoService.findByDepartamentoId(idDpto);
    }*/
}