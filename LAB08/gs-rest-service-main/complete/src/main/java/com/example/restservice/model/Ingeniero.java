package com.example.restservice.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Ingeniero")
public class Ingeniero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDIng")
    private Integer idIng;

    @Column(name = "Nombre", length = 100)
    private String nombre;

    @Column(name = "Especialidad", length = 100)
    private String especialidad;

    @Column(name = "Cargo", length = 50)
    private String cargo;

    @ManyToMany
    @JoinTable(
        name = "Ingeniero_Proyecto",  // nombre tabla intermedia
        joinColumns = @JoinColumn(name = "IDIng"),   // clave foránea en tabla intermedia que apunta a esta entidad
        inverseJoinColumns = @JoinColumn(name = "IDProy") // clave foránea en tabla intermedia que apunta a la otra entidad
    )
    private Set<Proyecto> proyectos;

    // Getters y setters
    public Integer getIdIng() {
        return idIng;
    }

    public void setIdIng(Integer idIng) {
        this.idIng = idIng;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}

