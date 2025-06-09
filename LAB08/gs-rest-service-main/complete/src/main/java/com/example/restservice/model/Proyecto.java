package com.example.restservice.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProy")
    private Integer idProy;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Fec_Inicio")
    @Temporal(TemporalType.DATE)
    private Date fecInicio;

    @Column(name = "Fec_Termino")
    @Temporal(TemporalType.DATE)
    private Date fecTermino;

    @ManyToOne
    @JoinColumn(name = "IDDpto", nullable = false)
    private Departamento departamento;

    @ManyToMany(mappedBy = "proyectos") // nombre del atributo en Ingeniero que mapea esta relación
    private Set<Ingeniero> ingenieros;

    // Getters y setters
    public Integer getIdProy() {
        return idProy;
    }

    public void setIdProy(Integer idProy) {
        this.idProy = idProy;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecTermino() {
        return fecTermino;
    }

    public void setFecTermino(Date fecTermino) {
        this.fecTermino = fecTermino;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}