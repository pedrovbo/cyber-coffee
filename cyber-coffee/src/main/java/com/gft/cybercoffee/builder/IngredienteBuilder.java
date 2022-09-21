package com.gft.cybercoffee.builder;

import com.gft.cybercoffee.model.Ingrediente;

public class IngredienteBuilder {
    
    private String nome;

    public IngredienteBuilder() {
    }
    
    public IngredienteBuilder nome(String nome){
        this.nome = nome;    
        return this;
    }

    public Ingrediente build(){
        return new Ingrediente(nome);
    }


}
