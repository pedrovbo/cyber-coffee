package com.gft.cybercoffee.builder;

import com.gft.cybercoffee.model.Receita;

public class ReceitaBuilder {

    private String nome;

    private int tempoPreparo;

    private String modoPreparo;

    private int rendimento;

    private String imagem;

    public ReceitaBuilder() {}

    public ReceitaBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ReceitaBuilder tempo(int tempo) {
        this.tempoPreparo = tempo;
        return this;
    }

    public ReceitaBuilder modo(String modo) {
        this.modoPreparo = modo;
        return this;
    }

    public ReceitaBuilder rendimento(int rendimento) {
        this.rendimento = rendimento;
        return this;
    }

    public ReceitaBuilder imagem(String imagem) {
        this.imagem = imagem;
        return this;
    }

    public Receita build() {
        return new Receita(nome, tempoPreparo, modoPreparo, rendimento);
    }
    
    public Receita buildComImagem() {
        return new Receita(nome, tempoPreparo, modoPreparo, rendimento, imagem);
    }

        
}
