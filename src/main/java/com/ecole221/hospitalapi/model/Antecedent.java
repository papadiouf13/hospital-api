package com.ecole221.hospitalapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
@Builder
@Entity
public class Antecedent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String libelle;
    @JsonIgnore
    @ManyToMany(mappedBy = "antecedents")
    private List<Patient> patients;

    public Antecedent(int id, String libelle, List<Patient> patients) {
        this.id = id;
        this.libelle = libelle;
        this.patients = patients;
    }

    public Antecedent(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Antecedent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
