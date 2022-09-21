package com.gft.cybercoffee;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import com.gft.cybercoffee.model.UnidadeMedida;

//TODO adicionar JACOCO para verificação de cobertura de testes

@SpringBootTest
public class UnidadeMedidaTest {
    // TODO testes
    private UnidadeMedida unidadeMedida;

    @BeforeEach
    void setup() {
        unidadeMedida = new UnidadeMedida();

        unidadeMedida.setNome("Xícara");
    }

}
