package com.gft.cybercoffee.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ingrediente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @OneToMany(mappedBy = "ingrediente")
    List<ElementoReceita> teste = new ArrayList<>();

    public Ingrediente(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return this.getNome();
    }
}
