package com.gft.cybercoffee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class CyberCoffeeUser {
    // TODO: verificar se precisa mudar o nome da classe

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String username;
    private String password;
    private SimpleGrantedAuthority authorities;

    @OneToMany
    private List<Receita> receita = new ArrayList<>();

    public CyberCoffeeUser(String nome, String username, String password, SimpleGrantedAuthority authorities,
            List<Receita> receita) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.receita = receita;
    }

    

}
