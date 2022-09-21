package com.gft.cybercoffee.builder;

import com.gft.cybercoffee.model.ElementoReceita;
import com.gft.cybercoffee.model.Ingrediente;
import com.gft.cybercoffee.model.Receita;
import com.gft.cybercoffee.model.UnidadeMedida;

public class ElementoReceitaBuilder {
    
    private double quantidade;
    private UnidadeMedida unidadeMedida;
    private Ingrediente ingrediente;
    private Receita receita;
    
    public ElementoReceitaBuilder() {
    }

    public ElementoReceitaBuilder quantidade(double quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ElementoReceitaBuilder unidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
        return this;
    }

    public ElementoReceitaBuilder ingrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
        return this;
    }

    public ElementoReceitaBuilder receita(Receita receita){
        this.receita = receita;
        return this;
    }
    
    public ElementoReceita build(){
        return new ElementoReceita(quantidade, unidadeMedida, ingrediente);
    }
}
