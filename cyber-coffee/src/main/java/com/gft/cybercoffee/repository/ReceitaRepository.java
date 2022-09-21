package com.gft.cybercoffee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.cybercoffee.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{
    
    List<Receita> findByNomeContains(String nome);

}
