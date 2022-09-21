package com.gft.cybercoffee.builder;

import com.gft.cybercoffee.model.UnidadeMedida;

public class UnidadeMedidaBuilder {

    private String nome;

    public UnidadeMedidaBuilder() {

    }

    public UnidadeMedidaBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public UnidadeMedida build() {
        return new UnidadeMedida(nome);
    }

}
