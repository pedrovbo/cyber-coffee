package com.gft.cybercoffee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.cybercoffee.model.UnidadeMedida;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long>{

    List<UnidadeMedida> findByNomeContains(String nome);
    
}
