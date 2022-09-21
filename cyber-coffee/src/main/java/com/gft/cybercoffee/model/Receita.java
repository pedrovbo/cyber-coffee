package com.gft.cybercoffee.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Receita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    private int tempoPreparo;

    @Column(length = 2000)
    private String modoPreparo;

    private int rendimento;

    private String imagem;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.REMOVE)
    private List<ElementoReceita> elementosReceita = new ArrayList<>();

    public Receita(String nome, int tempoPreparo, String modoPreparo, int rendimento) {
        this.nome = nome;
        this.tempoPreparo = tempoPreparo;
        this.modoPreparo = modoPreparo;
        this.rendimento = rendimento;
    }

    public void addElementoReceita(ElementoReceita elementoReceita) {
        elementosReceita.add(elementoReceita);
        elementoReceita.setReceita(this);
    }

    public void removeElementoReceita(ElementoReceita elementoReceita) {
        elementosReceita.remove(elementoReceita);
        elementoReceita.setReceita(null);
    }

    public List<Ingrediente> listarIngredientesReceita() {

        List<Ingrediente> ingredientes = new ArrayList<>();
        this.elementosReceita.forEach(i -> ingredientes.add(i.getIngrediente()));

        return ingredientes;
    }

    public void addAllElementosReceita(List<ElementoReceita> listaElementosReceita) {
        listaElementosReceita.forEach(i -> this.addElementoReceita(i));
    }

    public Receita(@NotEmpty String nome, int tempoPreparo, String modoPreparo, int rendimento, String imagem) {
        this.nome = nome;
        this.tempoPreparo = tempoPreparo;
        this.modoPreparo = modoPreparo;
        this.rendimento = rendimento;
        this.imagem = imagem;
    }

}
