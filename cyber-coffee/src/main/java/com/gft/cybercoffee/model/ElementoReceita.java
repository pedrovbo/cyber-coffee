package com.gft.cybercoffee.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ElementoReceita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantidade;

    @ManyToOne //(cascade = CascadeType.PERSIST)
    private UnidadeMedida unidadeMedida;
    @ManyToOne //(cascade = CascadeType.PERSIST)
    private Ingrediente ingrediente;

    // @JsonIgnore
    @ManyToOne
    private Receita receita;

    public ElementoReceita(double quantidade, UnidadeMedida unidadeMedida, Ingrediente ingrediente) {
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.ingrediente = ingrediente;
    }

    public ElementoReceita(double quantidade, UnidadeMedida unidadeMedida, Ingrediente ingrediente, Receita receita) {
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.ingrediente = ingrediente;
        this.receita = receita;
    }

    public ElementoReceita(UnidadeMedida unidadeMedida, Ingrediente ingrediente, Receita receita) {
        this.unidadeMedida = unidadeMedida;
        this.ingrediente = ingrediente;
        this.receita = receita;
    }

    public ElementoReceita(UnidadeMedida unidadeMedida, Ingrediente ingrediente) {
        this.unidadeMedida = unidadeMedida;
        this.ingrediente = ingrediente;
    }

    public ElementoReceita(double quantidade, Ingrediente ingrediente, Receita receita) {
        this.quantidade = quantidade;
        this.ingrediente = ingrediente;
        this.receita = receita;
    }

    public ElementoReceita(double quantidade, Ingrediente ingrediente) {
        this.quantidade = quantidade;
        this.ingrediente = ingrediente;
    }

    public ElementoReceita(Ingrediente ingrediente, Receita receita) {
        this.ingrediente = ingrediente;
        this.receita = receita;
    }

    public ElementoReceita(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

}
