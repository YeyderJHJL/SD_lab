package com.example.restservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDpto")
    private Integer idDpto;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Telefono", length = 20)
    private String telefono;

    @Column(name = "Fax", length = 20)
    private String fax;

    public Integer getIdDpto() {
        return idDpto;
    }

    public void setIdDpto(Integer idDpto) {
        this.idDpto = idDpto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
