package com.example.restservice.service;

import com.example.restservice.model.Proyecto;
import com.example.restservice.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> findAll() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> findById(Integer id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto save(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public void deleteById(Integer id) {
        proyectoRepository.deleteById(id);
    }

    /*public List<Proyecto> findByDepartamentoId(Integer idDpto) {
        return proyectoRepository.findByIdDpto(idDpto);
    }*/
}
